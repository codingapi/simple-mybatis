package com.codingapi.simplemybatis.tree;

import lombok.Data;

import java.util.List;

@Data
public class TreeList<T> {

    T node;

    List<TreeList<T>> children;

    public TreeList(T node) {
        this.node = node;
    }


}
