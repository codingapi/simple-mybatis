package com.codingapi.simplemybatis.provider;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lorne
 * @since 1.0.0
 */
public class AutoGeneratorProvider {

    public String create(org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,null);
        return SimpleMybatisProviderContext.getInstance().getBuilder(AutoGeneratorSQLBuilder.class).create(simpleProviderContext);
    }

    public String drop(org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,null);
        return SimpleMybatisProviderContext.getInstance().getBuilder(AutoGeneratorSQLBuilder.class).drop(simpleProviderContext);
    }
}
