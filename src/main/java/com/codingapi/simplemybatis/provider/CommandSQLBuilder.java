package com.codingapi.simplemybatis.provider;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lorne
 * @since 1.0.0
 */
public interface CommandSQLBuilder extends SimpleMybatisSQLBuilder {

    String insertSql(SimpleProviderContext context)throws IllegalAccessException, InvocationTargetException;

    String insertAllSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException,InvocationTargetException;

    String deleteSql(SimpleProviderContext simpleProviderContext)throws IllegalAccessException, InvocationTargetException  ;

    String deleteAllSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException,InvocationTargetException;

    String deleteAllByIdSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException,InvocationTargetException;

    String deleteByIdSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException,InvocationTargetException;

    String updateSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException,InvocationTargetException;
}
