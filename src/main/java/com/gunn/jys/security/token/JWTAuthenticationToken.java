package com.gunn.jys.security.token;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTAuthenticationToken implements AuthenticationToken {

    private Object userId;
    private String token;

    public JWTAuthenticationToken(Object var1, String var2) {
        this.userId = var1;
        this.token = var2;
    }

    @Override
    public Object getPrincipal() {
        return this.getUserId();
    }

    @Override
    public Object getCredentials() {
        return this.getToken();
    }

    public Object getUserId() {
        return this.userId;
    }

    public void setUserId(long var1) {
        this.userId = var1;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String var1) {
        this.token = var1;
    }
}
