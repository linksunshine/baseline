package com.linksunshine.baseline.server.web.security;

import com.linksunshine.baseline.server.web.dto.UserDTO;
import com.linksunshine.baseline.server.web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ucmed on 2017/3/28.
 */
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    private CacheManager shiroEhcacheManager;

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userId = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(userId));
        authorizationInfo.setStringPermissions(userService.findPermissions(userId));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userId = (String) authenticationToken.getPrincipal();

        UserDTO usersDTO = userService.findByUserId(userId);

        if (usersDTO == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                usersDTO.getUserId(), //用户Id
                usersDTO.getPassword(), //密码
                ByteSource.Util.bytes(usersDTO.getUsername()+usersDTO.getSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    public void clearAllCache(Object key) {
        getAuthenticationCache().remove(key);
        getAuthorizationCache().remove(key);
        getPasswordRetryCache().remove(key);
    }

    public Cache<Object, AuthenticationInfo> getPasswordRetryCache() {
        return shiroEhcacheManager.getCache("passwordRetryCache");
    }

}
