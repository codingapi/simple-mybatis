package com.codingapi.simplemybatis.utils;

import java.util.Map;
import java.util.Set;

/**
 * @author lorne
 * @date 2020/4/17
 * @description
 */
public class MapCamelUtils {

    public static void camelMap(Map<String, Object> item) {
        Set<String> keys = item.keySet();
        for (String key : keys) {
            String newKey = StringCharacterUtils.underlineToCamel(key);
            if (!newKey.equals(key)) {
                item.put(newKey, item.get(key));
            }
        }
    }

}
