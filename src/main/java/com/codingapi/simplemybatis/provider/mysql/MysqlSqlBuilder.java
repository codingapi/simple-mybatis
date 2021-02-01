package com.codingapi.simplemybatis.provider.mysql;

import com.codingapi.simplemybatis.provider.SimpleMybatisProviderContext;

/**
 * @author lorne
 * @since 1.0.0
 */
public class MysqlSqlBuilder {

    static {
        SimpleMybatisProviderContext.getInstance().registerSQLBuilder(new MysqlCommandSQLBuilder());
        SimpleMybatisProviderContext.getInstance().registerSQLBuilder(new MysqlQuerySQLBuilder());
    }
}
