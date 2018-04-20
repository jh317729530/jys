package com.gunn.jys.vo.node;

import java.util.List;

public class DisabledNode extends Node {

    private boolean disabled = false;

    public DisabledNode() {
        super(true);
    }

    public DisabledNode(String name) {
        super(true);
        setName(name);
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setDisabledChildren(List<? extends DisabledNode> children) {
        super.setChildren(children);
        if (children == null || children.size() == 0) {
            this.disabled = true;
            return ;
        }

        for (DisabledNode node : children) {
            boolean flag = node.getDisabled();
            if (!flag) {
                return ;
            }

        }
        this.disabled = true;
    }
}
