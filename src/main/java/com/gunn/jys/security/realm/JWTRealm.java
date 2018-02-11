package com.gunn.jys.security.realm;

import com.gunn.jys.bo.JysSubject;
import com.gunn.jys.entity.User;
import com.gunn.jys.mapper.UserMapper;
import com.gunn.jys.security.token.JWTAuthenticationToken;
import com.gunn.jys.util.JsonUtil;
import com.gunn.jys.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class JWTRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JWTAuthenticationToken;
    }

    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        return new SimpleCredentialsMatcher();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String json = (String) principals.getPrimaryPrincipal();
        JysSubject jysSubject = JsonUtil.fromJson(json, JysSubject.class);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken)token;
        String userIdJson = (String) jwtAuthenticationToken.getUserId();
        if (StringUtils.isBlank(userIdJson)) {
            return null;
        }

        String authenticationToken = jwtAuthenticationToken.getToken();
        if (JwtUtil.validateToken(authenticationToken)) {
            JysSubject jysSubject = JsonUtil.fromJson(authenticationToken, JysSubject.class);
            SimpleAccount account = null;
            Integer userId = jysSubject.getId();
            User user = userMapper.selectByPrimaryKey(userId);
            if (null != user) {
                account = new SimpleAccount(userIdJson, authenticationToken, getName());
                return account;
            }
        }
        return null;
    }
}
