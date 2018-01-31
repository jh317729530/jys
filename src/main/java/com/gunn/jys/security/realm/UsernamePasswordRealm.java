package com.gunn.jys.security.realm;

import com.gunn.jys.bo.JysSubject;
import com.gunn.jys.constant.common.EncryptConst;
import com.gunn.jys.entity.User;
import com.gunn.jys.mapper.UserMapper;
import com.gunn.jys.service.UserService;
import com.gunn.jys.util.JsonUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class UsernamePasswordRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(EncryptConst.ALGORITHM_MD5);
        matcher.setHashIterations(EncryptConst.ITERATION_TIMES);
        return matcher;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     *  验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        if (null == usernamePasswordToken) {
            return null;
        }
        String json = (String) authenticationToken.getPrincipal();
        JysSubject jysSubject = JsonUtil.fromJson(json, JysSubject.class);
        Integer id = jysSubject.getId();
        User user = userMapper.selectByPrimaryKey(id);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(json, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return simpleAuthenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
}
