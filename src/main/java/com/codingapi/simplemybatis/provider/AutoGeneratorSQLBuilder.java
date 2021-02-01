package com.codingapi.simplemybatis.provider;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lorne
 * @since 1.0.0
 */
public interface AutoGeneratorSQLBuilder extends SimpleMybatisSQLBuilder{

     String create(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException ;

     String drop(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException ;

}
