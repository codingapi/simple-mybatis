package com.codingapi.simplemybatis.provider;

import java.lang.reflect.InvocationTargetException;


public interface QuerySQLBuilder extends SimpleMybatisSQLBuilder {

    String getById(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException ;

    String findAll(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException ;

    String query(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException ;


    String queryView(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException;

}
