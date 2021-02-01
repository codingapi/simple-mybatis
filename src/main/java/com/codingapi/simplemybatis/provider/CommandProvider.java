package com.codingapi.simplemybatis.provider;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CommandProvider  {

    public String save(Object obj, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,obj);
        return SimpleMybatisProviderContext.getInstance().getBuilder(CommandSQLBuilder.class).insertSql(simpleProviderContext);
    }

    public String saveAll(Map<String, Object> map, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,map);
        return SimpleMybatisProviderContext.getInstance().getBuilder(CommandSQLBuilder.class).insertAllSql(simpleProviderContext);
    }

    public String deleteAll(Map<String, Object> map, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,map);
        return SimpleMybatisProviderContext.getInstance().getBuilder(CommandSQLBuilder.class).deleteAllSql(simpleProviderContext);
    }

    public String deleteAllById(Map<String, Object> map, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,map);
        return SimpleMybatisProviderContext.getInstance().getBuilder(CommandSQLBuilder.class).deleteAllByIdSql(simpleProviderContext);
    }

    public String deleteById(Object obj, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,obj);
        return SimpleMybatisProviderContext.getInstance().getBuilder(CommandSQLBuilder.class).deleteByIdSql(simpleProviderContext);
    }

    public String delete(Object obj, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,obj);
        return SimpleMybatisProviderContext.getInstance().getBuilder(CommandSQLBuilder.class).deleteSql(simpleProviderContext);
    }


    public String update(Object obj, org.apache.ibatis.builder.annotation.ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SimpleProviderContext simpleProviderContext = new SimpleProviderContext(context,obj);
        return SimpleMybatisProviderContext.getInstance().getBuilder(CommandSQLBuilder.class).updateSql(simpleProviderContext);
    }





}
