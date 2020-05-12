package com.codingapi.simplemybatis.mapper;

import com.codingapi.simplemybatis.provider.QueryProvider;
import com.codingapi.simplemybatis.query.Query;
import com.codingapi.simplemybatis.utils.MapBeanUtils;
import com.codingapi.simplemybatis.utils.MapCamelUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 常用的查询操作
 *
 * @param <T>
 */
public interface QueryMapper<T> extends BaseMapper<T> {

    @SelectProvider(type = QueryProvider.class, method = "getById")
    T getById(@Param("id") Object id);

    @SelectProvider(type = QueryProvider.class, method = "findAll")
    List<T> findAll();

    @SelectProvider(type = QueryProvider.class, method = "query")
    List<T> query(@Param("query") Query query);

    @SelectProvider(type = QueryProvider.class, method = "queryView")
    List<Map<String, Object>> queryMap(@Param("query") Query query);

    @SuppressWarnings("unchecked")
    default <V> List<V> queryView(Class<V> clazz, Query query) {
        List<Map<String, Object>> list = queryMap(query);
        return list.stream().map(item -> {
            MapCamelUtils.camelMap(item);
            if (Map.class.isAssignableFrom(clazz)) {
                return (V) item;
            }
            return MapBeanUtils.toBean(clazz, item);
        }).collect(Collectors.toList());
    }

}
