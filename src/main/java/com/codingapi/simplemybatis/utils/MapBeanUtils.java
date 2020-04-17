package com.codingapi.simplemybatis.utils;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author lorne
 * @date 2020/4/17
 * @description
 */
public class MapBeanUtils {

    @SneakyThrows
    public static <T> T toBean(Class<T> clazz, Map<String, Object> map) {
        T t = clazz.newInstance();
        BeanUtils.populate(t, map);
        return t;
    }
}
