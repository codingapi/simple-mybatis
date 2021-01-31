package com.codingapi.simplemybatis.tree;


import java.util.List;

public class TreeList<T> {

    private T node;

    private List<TreeList<T>> children;

    public TreeList(T node) {
        this.node = node;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public List<TreeList<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeList<T>> children) {
        this.children = children;
    }
}
