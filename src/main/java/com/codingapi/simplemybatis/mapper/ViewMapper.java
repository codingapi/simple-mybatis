package com.codingapi.simplemybatis.mapper;

import com.codingapi.simplemybatis.page.PageList;
import com.codingapi.simplemybatis.page.PageRequest;
import com.codingapi.simplemybatis.provider.QueryProvider;
import com.codingapi.simplemybatis.query.Query;
import com.codingapi.simplemybatis.utils.MapBeanUtils;
import com.codingapi.simplemybatis.utils.MapCamelUtils;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author lorne
 * @date 2020/4/27
 * @description
 */
public interface ViewMapper {

    @SelectProvider(type = QueryProvider.class, method = "queryView")
    List<Map<String, Object>> queryMap(@Param("query") Query query);

    default <V> List<V> queryView(Class<V> clazz, Query query) {
        List<Map<String, Object>> list = queryMap(query);
        return list.stream().map(item -> {
            MapCamelUtils.camelMap(item);
            return MapBeanUtils.toBean(clazz, item);
        }).collect(Collectors.toList());
    }

    default <T> PageList<T> page(int nowPage, int pageSize, Supplier<List<T>> supplier) {
        PageHelper.startPage(nowPage, pageSize);
        return new PageList<>(supplier.get());
    }

    default <T> PageList<T> page(PageRequest request, Supplier<List<T>> supplier) {
        return page(request.getNowPage(), request.getPageSize(), supplier);
    }


}
