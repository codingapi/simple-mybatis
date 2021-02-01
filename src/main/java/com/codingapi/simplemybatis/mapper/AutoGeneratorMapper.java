package com.codingapi.simplemybatis.mapper;

import com.codingapi.simplemybatis.provider.AutoGeneratorProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author lorne
 * @since 1.0.0
 */
public interface AutoGeneratorMapper<T> extends BaseMapper<T>{

    @UpdateProvider(type = AutoGeneratorProvider.class, method = "create")
    void create();

    @UpdateProvider(type = AutoGeneratorProvider.class, method = "drop")
    void drop();

}
