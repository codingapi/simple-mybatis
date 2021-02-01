package com.codingapi.simplemybatis.provider;

import com.codingapi.simplemybatis.provider.mysql.MysqlSqlParser;
import com.codingapi.simplemybatis.query.Query;
import com.codingapi.simplemybatis.query.SqlBuilder;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.InvocationTargetException;


public class QueryProvider {

    public String getById(Object obj, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,obj);
        return SimpleMybatisProviderContext.getInstance().getBuilder(QuerySQLBuilder.class).getById(simpleProviderContext);
    }

    public String findAll(org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,null);
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(simpleProviderContext.nullTableInfo());
        return mysqlSqlParser.createSelectAll();
    }


    public String query(@Param("query") Query query, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,query);
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(simpleProviderContext.nullTableInfo());
        return mysqlSqlParser.createQuery(query);
    }


    public String queryView(@Param("query") Query query, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,query);
        SqlBuilder sqlBuilder = new SqlBuilder(query.getSelect(), null, query);
        return sqlBuilder.getSql();
    }


}
