package com.gunn.jys.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_teacher")
public class Teacher {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private Integer userId;

    private String name;

    private Integer sex;

    private Integer age;

    private Integer isDirector;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIsDirector() {
        return isDirector;
    }

    public void setIsDirector(Integer isDirector) {
        this.isDirector = isDirector;
    }
}