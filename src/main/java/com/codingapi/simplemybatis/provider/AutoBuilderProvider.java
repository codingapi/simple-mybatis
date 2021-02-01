package com.codingapi.simplemybatis.provider;

import com.codingapi.simplemybatis.parser.TableInfo;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lorne
 * @since 1.0.0
 */
public class AutoBuilderProvider  {

    public String create(org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,null);
        TableInfo tableInfo = simpleProviderContext.nullTableInfo();
        return "create table "+tableInfo.getTableName();
    }

    public String drop(org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,null);
        TableInfo tableInfo = simpleProviderContext.nullTableInfo();
        return "drop table "+tableInfo.getTableName();
    }
}
