package com.codingapi.simplemybatis.provider.builder;

import com.codingapi.simplemybatis.provider.QuerySQLBuilder;
import com.codingapi.simplemybatis.provider.SimpleProviderContext;
import com.codingapi.simplemybatis.query.Query;
import com.codingapi.simplemybatis.query.SqlBuilder;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lorne
 * @since 1.0.0
 */
public class BasicQuerySQLBuilder implements QuerySQLBuilder {

    @Override
    public String getById(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        BasicSqlParser basicSqlParser = new BasicSqlParser(simpleProviderContext.nullTableInfo());
        return basicSqlParser.createGetByIdSql();
    }

    @Override
    public String findAll(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        BasicSqlParser basicSqlParser = new BasicSqlParser(simpleProviderContext.nullTableInfo());
        return basicSqlParser.createSelectAll();
    }

    @Override
    public String query(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        BasicSqlParser basicSqlParser = new BasicSqlParser(simpleProviderContext.nullTableInfo());
        Query query = simpleProviderContext.getParameter(Query.class);
        return basicSqlParser.createQuery(query);
    }

    @Override
    public String queryView(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        Query query = simpleProviderContext.getParameter(Query.class);
        SqlBuilder sqlBuilder = new SqlBuilder(query.getSelect(), null, query);
        return sqlBuilder.getSql();
    }
}
