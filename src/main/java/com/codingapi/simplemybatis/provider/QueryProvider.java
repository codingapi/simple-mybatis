package com.codingapi.simplemybatis.provider;

import com.codingapi.simplemybatis.parser.SqlParser;
import com.codingapi.simplemybatis.query.Query;
import com.codingapi.simplemybatis.query.QuerySqlBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.InvocationTargetException;


@Slf4j
public class QueryProvider extends BaseProvider {

    public String getById(Object obj, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SqlParser sqlParser = createParser(null, context);
        return sqlParser.createGetByIdSql();
    }

    public String findAll(ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SqlParser sqlParser = createParser(null, context);
        return sqlParser.createSelectAll();
    }


    public String query(@Param("query") Query query, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SqlParser sqlParser = createParser(null, context);
        return sqlParser.createQuery(query);
    }


    public String queryView(@Param("query") Query query, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        QuerySqlBuilder querySqlBuilder = new QuerySqlBuilder(query.getSelect(), null, query);
        return querySqlBuilder.getSql();
    }


}
