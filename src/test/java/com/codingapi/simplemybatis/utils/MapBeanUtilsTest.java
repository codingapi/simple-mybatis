package com.codingapi.simplemybatis.utils;

import com.codingapi.simplemybatis.entity.DemoView;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapBeanUtilsTest {

    @Test
    void toBean() {
        Map<String,Object> map = new HashMap<>();
        map.put("create_time","20200202");
        map.put("name","123");
        MapCamelUtils.camelMap(map);
        DemoView demoView =  MapBeanUtils.toBean(DemoView.class,map);
        assertTrue("20200202".equals(demoView.getCreateTime()),"creatTime not val");
    }
}