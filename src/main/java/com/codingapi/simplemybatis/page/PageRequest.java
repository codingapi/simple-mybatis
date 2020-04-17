package com.codingapi.simplemybatis.page;

import lombok.Data;

@Data
public class PageRequest {

    private int nowPage = 1;

    private int pageSize = 20;


}
