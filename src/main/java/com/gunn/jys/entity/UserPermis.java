package com.gunn.jys.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user_permis")
public class UserPermis {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private Integer userId;

    private Integer permisId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPermisId() {
        return permisId;
    }

    public void setPermisId(Integer permisId) {
        this.permisId = permisId;
    }
}