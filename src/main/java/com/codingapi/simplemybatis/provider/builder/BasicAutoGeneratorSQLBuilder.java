package com.codingapi.simplemybatis.provider.builder;

import com.codingapi.simplemybatis.parser.TableInfo;
import com.codingapi.simplemybatis.provider.AutoGeneratorSQLBuilder;
import com.codingapi.simplemybatis.provider.SimpleProviderContext;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lorne
 * @since 1.0.0
 */
public class BasicAutoGeneratorSQLBuilder implements AutoGeneratorSQLBuilder {

    @Override
    public String create(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        TableInfo tableInfo = simpleProviderContext.nullTableInfo();
        BasicTableBuilder builder = new BasicTableBuilder(tableInfo);
        builder.builder();
        return builder.sql();
    }

    @Override
    public String drop(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        TableInfo tableInfo = simpleProviderContext.nullTableInfo();
        return "drop table "+tableInfo.getTableName();
    }
}