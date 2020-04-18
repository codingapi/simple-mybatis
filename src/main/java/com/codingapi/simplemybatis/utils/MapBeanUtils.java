package com.codingapi.simplemybatis.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author lorne
 * @date 2020/4/17
 * @description
 */
public class MapBeanUtils {

    public static <T> T toBean(Class<T> clazz, Map<String, Object> map) {
        T t = null;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.populate(t, map);
            return t;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
