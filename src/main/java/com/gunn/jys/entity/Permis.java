package com.gunn.jys.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_permis")
public class Permis {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String permisName;

    private String permisDesc;

    private Integer permisModuleId;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermisName() {
        return permisName;
    }

    public void setPermisName(String permisName) {
        this.permisName = permisName == null ? null : permisName.trim();
    }

    public String getPermisDesc() {
        return permisDesc;
    }

    public void setPermisDesc(String permisDesc) {
        this.permisDesc = permisDesc == null ? null : permisDesc.trim();
    }

    public Integer getPermisModuleId() {
        return permisModuleId;
    }

    public void setPermisModuleId(Integer permisModuleId) {
        this.permisModuleId = permisModuleId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}