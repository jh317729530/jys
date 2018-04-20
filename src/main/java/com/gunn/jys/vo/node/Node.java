package com.gunn.jys.vo.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

public class Node {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nodeKey ;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<? extends Node> children;

    /**
     * 自动生成nodeKey
     */
    @JsonIgnore
    private boolean autoGeneratorKey;

    public Node() {
        this(false);
    }

    public Node(boolean autoGeneratorKey) {
        this.autoGeneratorKey = autoGeneratorKey;
    }

    public Node(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<? extends Node> getChildren() {
        return children;
    }

    public void setChildren(List<? extends Node> children) {
        this.children = children;
    }

    public String getNodeKey() {
        if (StringUtils.isBlank(nodeKey)) {
            if (autoGeneratorKey) {
                nodeKey = UUID.randomUUID().toString().replaceAll("-", "");
            }
        }
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }
}
