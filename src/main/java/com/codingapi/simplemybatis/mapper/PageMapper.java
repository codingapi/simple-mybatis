package com.codingapi.simplemybatis.mapper;

import com.codingapi.simplemybatis.page.IPageQuery;

/**
 * 支持分页和查询的操作
 *
 * @param <T>
 */
public interface PageMapper<T> extends QueryMapper<T>, IPageQuery<T> {

}
