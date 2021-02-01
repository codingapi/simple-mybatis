package com.codingapi.simplemybatis.provider.mysql;

import com.codingapi.simplemybatis.provider.QuerySQLBuilder;
import com.codingapi.simplemybatis.provider.SimpleProviderContext;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lorne
 * @since 1.0.0
 */
public class MysqlQuerySQLBuilder implements QuerySQLBuilder {

    @Override
    public String getById(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(simpleProviderContext.nullTableInfo());
        return mysqlSqlParser.createGetByIdSql();
    }

    @Override
    public String findAll(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        return null;
    }

    @Override
    public String query(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        return null;
    }

    @Override
    public String queryView(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        return null;
    }
}
