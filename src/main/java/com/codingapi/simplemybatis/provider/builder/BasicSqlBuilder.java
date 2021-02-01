package com.codingapi.simplemybatis.provider.builder;

import com.codingapi.simplemybatis.provider.SimpleMybatisProviderContext;

/**
 * @author lorne
 * @since 1.0.0
 */
public class BasicSqlBuilder {

    static {
        SimpleMybatisProviderContext.getInstance().registerSQLBuilder(new BasicCommandSQLBuilder());
        SimpleMybatisProviderContext.getInstance().registerSQLBuilder(new BasicQuerySQLBuilder());
        SimpleMybatisProviderContext.getInstance().registerSQLBuilder(new BasicAutoGeneratorSQLBuilder());
    }
}
