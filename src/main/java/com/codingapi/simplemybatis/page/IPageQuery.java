package com.codingapi.simplemybatis.page;

import com.github.pagehelper.PageHelper;

import java.util.List;
import java.util.function.Supplier;

public interface IPageQuery<T> {

    default <T> PageList<T> page(int nowPage, int pageSize, Supplier<List<T>> supplier) {
        PageHelper.startPage(nowPage, pageSize);
        return new PageList<>(supplier.get());
    }

    default <T> PageList<T> page(PageRequest request, Supplier<List<T>> supplier) {
        return page(request.getNowPage(), request.getPageSize(), supplier);
    }

}
