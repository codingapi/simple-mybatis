package com.codingapi.simplemybatis.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MapCamelUtilsTest {

    @Test
    void camelMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("title_name","123");
        MapCamelUtils.camelMap(map);
        assertTrue("123".equals(map.get("titleName")),"titleName not value");
    }
}