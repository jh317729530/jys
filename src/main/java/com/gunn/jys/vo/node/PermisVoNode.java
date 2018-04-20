package com.gunn.jys.vo.node;

public class PermisVoNode extends DisabledNode {

    private Integer permisId;

    private String permisName;

    private Integer hasPermis;

    public PermisVoNode() {
        super();
    }

    public PermisVoNode(String name, Integer permisId, String permisName, Integer hasPermis) {
        super(name);
        this.permisId = permisId;
        this.permisName = permisName;
        this.hasPermis = hasPermis;
    }

    public Integer getPermisId() {
        return permisId;
    }

    public void setPermisId(Integer permisId) {
        this.permisId = permisId;
    }

    public String getPermisName() {
        return permisName;
    }

    public void setPermisName(String permisName) {
        this.permisName = permisName;
    }

    public Integer getHasPermis() {
        return hasPermis;
    }

    public void setHasPermis(Integer hasPermis) {
        this.hasPermis = hasPermis;
    }
}
