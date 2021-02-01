package com.codingapi.simplemybatis.provider.mysql;

import com.codingapi.simplemybatis.parser.TableInfo;
import com.codingapi.simplemybatis.provider.CommandSQLBuilder;
import com.codingapi.simplemybatis.provider.SimpleProviderContext;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author lorne
 * @since 1.0.0
 */
public class MysqlCommandSQLBuilder implements CommandSQLBuilder {


    @Override
    public String insertSql(SimpleProviderContext context) throws IllegalAccessException, InvocationTargetException {
        TableInfo tableInfo = context.tableInfo();
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(tableInfo);
        return mysqlSqlParser.createInsertSql();
    }

    @Override
    public String insertAllSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        List list = (List) simpleProviderContext.getParameter(Map.class).get("list");
        if (list == null || list.size() == 0) {
            throw new RuntimeException("not data.");
        }
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(simpleProviderContext.nullTableInfo());
        return mysqlSqlParser.createInsertAllSql(list);
    }

    @Override
    public String deleteSql(SimpleProviderContext simpleProviderContext)throws IllegalAccessException, InvocationTargetException  {
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(simpleProviderContext.nullTableInfo());
        return mysqlSqlParser.createDeleteSql();
    }

    @Override
    public String deleteByIdSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(simpleProviderContext.nullTableInfo());
        return mysqlSqlParser.createDeleteSql();
    }


    @Override
    public String deleteAllByIdSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(simpleProviderContext.nullTableInfo());
        return mysqlSqlParser.createDeleteAllByIdSql();
    }

    @Override
    public String deleteAllSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(simpleProviderContext.nullTableInfo());
        return mysqlSqlParser.createDeleteAllSql();
    }

    @Override
    public String updateSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        MysqlSqlParser mysqlSqlParser = new MysqlSqlParser(simpleProviderContext.tableInfo());
        return mysqlSqlParser.createUpdateSql();
    }
}
