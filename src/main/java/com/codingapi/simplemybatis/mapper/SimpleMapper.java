package com.codingapi.simplemybatis.mapper;


/**
 * 常用的mapper 支持增删改查和分页
 *
 * @param <T>
 */
public interface SimpleMapper<T> extends CommonMapper<T>, PageMapper<T> {

}
