package com.codingapi.simplemybatis.provider;

import com.codingapi.simplemybatis.mapper.BaseMapper;
import com.codingapi.simplemybatis.parser.TableInfo;
import com.codingapi.simplemybatis.parser.TableParser;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.stream.Stream;


public final class SimpleProviderContext {

    private ProviderContext providerContext;
    private Object parameter;

    SimpleProviderContext(ProviderContext providerContext, Object parameter) {
        this.providerContext = providerContext;
        this.parameter = parameter;
    }

    public Object getParameter() {
        return parameter;
    }

    public <T> T getParameter(Class<T> clazz) {
        return (T)parameter;
    }

    public ProviderContext getProviderContext() {
        return providerContext;
    }

    private Class<?> entityType() {
        return Stream.of(providerContext.getMapperType().getGenericInterfaces())
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
                        "not find BaseMapper type " + providerContext.getMapperType().getName() + "."));
    }

    public TableInfo tableInfo()
            throws IllegalAccessException, InvocationTargetException {
        Class<?> clazz = entityType();
        TableParser tableParser = new TableParser(clazz);
        return tableParser.parser(parameter);
    }

    public TableInfo nullTableInfo()
            throws IllegalAccessException, InvocationTargetException {
        Class<?> clazz = entityType();
        TableParser tableParser = new TableParser(clazz);
        return tableParser.parser(null);
    }

}
