package com.whitecode.web.controller;

import com.whitecode.common.JsonResult;
import com.whitecode.entity.SysUser;
import com.whitecode.enums.ResultEnum;
import com.whitecode.service.SysUserService;
import com.whitecode.tools.EmailUtils;
import com.whitecode.tools.GenerateLinkUtils;
import com.whitecode.tools.JsonResultUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 邮箱找回密码控制器
 * Created by White on 2017/9/26.
 */
@RestController
@RequestMapping("/common/password")
public class PasswordController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "根据账户或邮箱地址发送重置密码邮件")
    @ApiImplicitParam(name = "username",value = "账户或邮箱地址",required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/findPassword", method = RequestMethod.GET)
    public JsonResult forgetPass(@RequestParam("username") String username){
        SysUser user = sysUserService.findByUsername(username);
        // 判断用户的输入是否正确
        if (username.equals("")) {
            return JsonResultUtils.error(ResultEnum.EMAIL_EMPTY);
        } else if (user == null) {
            return JsonResultUtils.error(ResultEnum.EMAIL_NOT_EXIST);
        }
        // 生成密钥
        String secretKey = UUID.randomUUID().toString();
        user.setSecretKey(secretKey);
        // 保存到数据库，方便校验
        sysUserService.updateUser(user);
        try {
            EmailUtils.sendResetPasswordEmail(sysUserService.findByUsername(username));
            return JsonResultUtils.success(ResultEnum.EMAIL_SEND_SUCCESS);
        }catch (Exception e) {
            return JsonResultUtils.error(ResultEnum.EMAIL_SEND_ERROR);
        }
    }
    @ApiOperation(value = "判断生成的重置密码邮件链接是否有效")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账户或邮箱地址", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "checkCode", value = "加密字符串", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/resetPassword",method = RequestMethod.GET)
    public JsonResult checkResetLink(@RequestParam("userName") String userName, @RequestParam("checkCode")String checkCode, HttpServletRequest request) {
        SysUser sysUser = sysUserService.findByUsername(userName);
        // 判断生成的链接地址是否有效
        if (checkCode.equals("") || userName.equals("")) {
            return JsonResultUtils.error(ResultEnum.EMAIL_LINK_NOT_FULL);
        } else if (sysUser == null) {
            return JsonResultUtils.error(ResultEnum.EMIAL_LINK_MATCH_USER);
        } else if (!GenerateLinkUtils.verifyCheckcode(sysUser, request)) {
            return JsonResultUtils.error(ResultEnum.EMAIL_LINK_EXPIRED);
        }
        return JsonResultUtils.success();
    }

}
