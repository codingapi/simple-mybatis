package com.codingapi.simplemybatis.page;


public class PageRequest {

    private int nowPage = 1;

    private int pageSize = 20;


    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
