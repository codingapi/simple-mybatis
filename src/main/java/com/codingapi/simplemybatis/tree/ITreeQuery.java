package com.codingapi.simplemybatis.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public interface ITreeQuery<T extends ITree<ID>, ID> {


    default List<TreeList<T>> tree(Supplier<List<T>> supplier, ID rootId) {
        List<T> list = supplier.get();
        List<TreeList<T>> trees = new ArrayList<>();
        for (T item : list) {
            if (Objects.equals(item.getSuperId(), rootId)) {
                TreeList<T> node = new TreeList<T>(item);
                addChild(node, list);
                trees.add(node);
            }
        }
        return trees;
    }


    default void addChild(TreeList<T> node, List<T> list) {
        List<TreeList<T>> childes = new ArrayList<>();
        for (T item : list) {
            if (Objects.equals(item.getSuperId(), node.getNode().getId())) {
                TreeList<T> child = new TreeList<T>(item);
                childes.add(child);
                addChild(child, list);
            }
        }
        node.setChildren(childes);
    }

}
