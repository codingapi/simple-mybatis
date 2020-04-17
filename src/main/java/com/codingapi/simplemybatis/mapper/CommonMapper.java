package com.codingapi.simplemybatis.mapper;

import com.codingapi.simplemybatis.provider.CommandProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 常用的写操作
 *
 * @param <T>
 */
public interface CommonMapper<T> extends BaseMapper<T> {

    @InsertProvider(type = CommandProvider.class, method = "save")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(T t);

    @InsertProvider(type = CommandProvider.class, method = "saveAll")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveAll(@Param("list") List<T> list);

    @UpdateProvider(type = CommandProvider.class, method = "update")
    int update(T t);

    @DeleteProvider(type = CommandProvider.class, method = "delete")
    int delete(T t);

    @DeleteProvider(type = CommandProvider.class, method = "deleteAll")
    int deleteAll(@Param("list") List<T> list);

    @DeleteProvider(type = CommandProvider.class, method = "deleteById")
    int deleteById(@Param("id") Object id);

    @DeleteProvider(type = CommandProvider.class, method = "deleteAllById")
    int deleteAllById(@Param("list") List id);


}
