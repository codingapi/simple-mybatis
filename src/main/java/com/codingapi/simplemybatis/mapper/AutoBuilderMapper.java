package com.codingapi.simplemybatis.mapper;

import com.codingapi.simplemybatis.provider.AutoBuilderProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author lorne
 * @since 1.0.0
 */
public interface AutoBuilderMapper<T> extends BaseMapper<T>{

    @UpdateProvider(type = AutoBuilderProvider.class, method = "create")
    void create();

    @UpdateProvider(type = AutoBuilderProvider.class, method = "drop")
    void drop();

}
