package com.gunn.jys.bo;

import com.gunn.jys.util.JsonUtil;
import com.gunn.jys.util.JwtUtil;

public class JysSubject {

    private Integer id;

    private String username;

    private Integer isAdmin;

    public JysSubject(Integer id,String username,Integer isAdmin){
        super();
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public String toToken() {
        return JwtUtil.sign(username);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }
}
