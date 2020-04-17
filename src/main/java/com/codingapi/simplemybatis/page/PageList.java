package com.codingapi.simplemybatis.page;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageList<T> {

    private Long total;
    private int pageSize;
    private int currentPage;
    private List<T> list;

    public PageList() {
    }

    public PageList(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        this.total = pageInfo.getTotal();
        this.pageSize = pageInfo.getPageSize();
        this.currentPage = pageInfo.getPageNum();
        this.list = pageInfo.getList();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
