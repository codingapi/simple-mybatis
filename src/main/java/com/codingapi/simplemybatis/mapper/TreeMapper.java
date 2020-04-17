package com.codingapi.simplemybatis.mapper;

import com.codingapi.simplemybatis.tree.ITree;
import com.codingapi.simplemybatis.tree.ITreeQuery;


/**
 * 支持返回树结构数据的mapper
 *
 * @param <T>
 * @param <ID>
 */
public interface TreeMapper<T extends ITree<ID>, ID> extends QueryMapper<T>, ITreeQuery<T, ID> {

}
