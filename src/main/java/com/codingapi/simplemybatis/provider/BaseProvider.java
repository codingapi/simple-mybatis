package com.codingapi.simplemybatis.provider;

import com.codingapi.simplemybatis.mapper.*;
import com.codingapi.simplemybatis.parser.SqlParser;
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
                .filter(type -> (type.getRawType() == BaseMapper.class
                        || type.getRawType() == CommonMapper.class
                        || type.getRawType() == QueryMapper.class
                        || type.getRawType() == SimpleMapper.class
                        || type.getRawType() == TreeMapper.class
                        || type.getRawType() == PageMapper.class)
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
        tableParser.parser(obj);
        return new SqlParser(tableParser.getTableInfo());
    }

}
