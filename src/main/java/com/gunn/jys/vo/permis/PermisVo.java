package com.gunn.jys.vo.permis;

public class PermisVo {

    private Integer id;

    private String name;

    private Integer parentId;

    private Integer hasChild;

    private String permisUrl;

    private Integer hasPermis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHasPermis() {
        return hasPermis;
    }

    public void setHasPermis(Integer hasPermis) {
        this.hasPermis = hasPermis;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getHasChild() {
        return hasChild;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
    }

    public String getPermisUrl() {
        return permisUrl;
    }

    public void setPermisUrl(String permisUrl) {
        this.permisUrl = permisUrl;
    }
}
