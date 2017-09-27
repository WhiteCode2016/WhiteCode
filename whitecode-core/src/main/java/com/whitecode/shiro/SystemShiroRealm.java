package com.whitecode.shiro;

import com.whitecode.entity.SysPermission;
import com.whitecode.entity.SysRole;
import com.whitecode.entity.SysUser;
import com.whitecode.enums.StatusEnum;
import com.whitecode.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;

/**
 * Shiro身份校验核心类
 * Created by White on 2017/9/11.
 */
public class SystemShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(SystemShiroRealm.class);

    @Resource
    private SysUserService sysUserService;

    /**
     * 权限验证（验证某个已认证的用户是否拥有某个权限）
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("权限配置中..." +principals.getPrimaryPrincipal());
        SimpleAuthorizationInfo authorizationInfo  = new SimpleAuthorizationInfo ();
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        for (SysRole role : sysUser.getRoleList()) {
            authorizationInfo .addRole(role.getRole());
            logger.info(role.getRole());
            for (SysPermission permission : role.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 认身份验证（验证用户输入的账号和密码是否正确）
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户输入的账号
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // 通过username从数据库中查找用户
        SysUser sysUser = sysUserService.findByUsername(username);
        if (sysUser == null) {
            throw new UnknownAccountException();
        } else if (!password.equals(sysUser.getPassword())) {
            throw new IncorrectCredentialsException();
        } else if (sysUser.getStatus() == StatusEnum.LOCKED) {
            throw new LockedAccountException();
        } else if (sysUser.getStatus() == StatusEnum.DISABLED) {
            throw new DisabledAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser, // 用户名
                sysUser.getPassword(), // 密码
                getName()
        );
        return authenticationInfo;
    }
}
