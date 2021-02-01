package com.codingapi.simplemybatis.provider;

import com.codingapi.simplemybatis.query.Query;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.InvocationTargetException;


public class QueryProvider {

    public String getById(Object obj, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,obj);
        return SimpleMybatisProviderContext.getInstance().getBuilder(QuerySQLBuilder.class).getById(simpleProviderContext);
    }

    public String findAll(org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,null);
        return SimpleMybatisProviderContext.getInstance().getBuilder(QuerySQLBuilder.class).findAll(simpleProviderContext);
    }


    public String query(@Param("query") Query query, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,query);
        return SimpleMybatisProviderContext.getInstance().getBuilder(QuerySQLBuilder.class).query(simpleProviderContext);
    }


    public String queryView(@Param("query") Query query, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,query);
        return SimpleMybatisProviderContext.getInstance().getBuilder(QuerySQLBuilder.class).queryView(simpleProviderContext);
    }


}
