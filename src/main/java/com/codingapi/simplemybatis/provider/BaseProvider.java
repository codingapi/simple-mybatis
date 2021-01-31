package com.codingapi.simplemybatis.provider;

import com.codingapi.simplemybatis.mapper.*;
import com.codingapi.simplemybatis.parser.SqlParser;
import com.codingapi.simplemybatis.parser.TableInfo;
import com.codingapi.simplemybatis.parser.TableParser;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.stream.Stream;


public class BaseProvider {

    protected Class<?> entityType(ProviderContext context) {
        return Stream.of(context.getMapperType().getGenericInterfaces())
                .filter(ParameterizedType.class::isInstance)
                .map(ParameterizedType.class::cast)
                .filter(
                        (type) ->
                                type.getRawType().getClass().isInstance(BaseMapper.class)
                )
                .findFirst()
                .map(type -> type.getActualTypeArguments()[0])
                .filter(Class.class::isInstance).map(Class.class::cast)
                .orElseThrow(() -> new IllegalStateException(
                        "not find BaseMapper type " + context.getMapperType().getName() + "."));
    }

    protected SqlParser createParser(Object obj, ProviderContext context)
            throws IllegalAccessException, InvocationTargetException {
        Class clazz = entityType(context);
        TableParser tableParser = new TableParser(clazz);
        TableInfo tableInfo = tableParser.parser(obj);
        return new SqlParser(tableInfo);
    }

}
