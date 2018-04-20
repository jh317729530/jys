package com.gunn.jys.vo.permis;

import com.gunn.jys.vo.node.Node;
import com.gunn.jys.vo.node.PermisVoNode;

import java.util.List;

public class PermisNodeVo {

    private Node root;

    private List<Integer> checkPermisIds;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public List<Integer> getCheckPermisIds() {
        return checkPermisIds;
    }

    public void setCheckPermisIds(List<Integer> checkPermisIds) {
        this.checkPermisIds = checkPermisIds;
    }
}
