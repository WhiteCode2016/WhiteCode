package com.whitecode.web.controller;

import com.whitecode.dto.MultipartFileParam;
import com.whitecode.service.FileService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * 结合WebUploader前端插件，实现大文件分片上传，适合（500MB < 文件大小 < 1GB）
 * Created by White on 2017/11/27.
 */
@Controller
@RequestMapping("/common/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/")
    public String index() {
        return "/fileUpload";
    }

    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity fileUpload(MultipartFileParam param, HttpServletRequest request) throws Exception {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            fileService.uploadFileRandomAccessFile(param);
        }
        return ResponseEntity.ok().body("上传成功");
    }
}
