package com.codingapi.simplemybatis.provider.builder;

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
public class BasicCommandSQLBuilder implements CommandSQLBuilder {


    @Override
    public String insertSql(SimpleProviderContext context) throws IllegalAccessException, InvocationTargetException {
        TableInfo tableInfo = context.tableInfo();
        BasicSqlParser basicSqlParser = new BasicSqlParser(tableInfo);
        return basicSqlParser.createInsertSql();
    }

    @Override
    public String insertAllSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        List list = (List) simpleProviderContext.getParameter(Map.class).get("list");
        if (list == null || list.size() == 0) {
            throw new RuntimeException("not data.");
        }
        BasicSqlParser basicSqlParser = new BasicSqlParser(simpleProviderContext.nullTableInfo());
        return basicSqlParser.createInsertAllSql(list);
    }

    @Override
    public String deleteSql(SimpleProviderContext simpleProviderContext)throws IllegalAccessException, InvocationTargetException  {
        BasicSqlParser basicSqlParser = new BasicSqlParser(simpleProviderContext.nullTableInfo());
        return basicSqlParser.createDeleteSql();
    }

    @Override
    public String deleteByIdSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        BasicSqlParser basicSqlParser = new BasicSqlParser(simpleProviderContext.nullTableInfo());
        return basicSqlParser.createDeleteSql();
    }


    @Override
    public String deleteAllByIdSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        BasicSqlParser basicSqlParser = new BasicSqlParser(simpleProviderContext.nullTableInfo());
        return basicSqlParser.createDeleteAllByIdSql();
    }

    @Override
    public String deleteAllSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        BasicSqlParser basicSqlParser = new BasicSqlParser(simpleProviderContext.nullTableInfo());
        return basicSqlParser.createDeleteAllSql();
    }

    @Override
    public String updateSql(SimpleProviderContext simpleProviderContext) throws IllegalAccessException, InvocationTargetException {
        BasicSqlParser basicSqlParser = new BasicSqlParser(simpleProviderContext.tableInfo());
        return basicSqlParser.createUpdateSql();
    }
}
