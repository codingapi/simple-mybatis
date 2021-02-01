package com.codingapi.simplemybatis.parser;

import com.codingapi.simplemybatis.entity.Demo;
import com.codingapi.simplemybatis.provider.mysql.MysqlSqlParser;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

class SqlParserTest {


    private Demo createDemo(){
        Demo demo = new Demo();
        demo.setId(1L);
        demo.setName("test");
        demo.setFlag(false);
        demo.setTest("123");
        return demo;
    }

    private MysqlSqlParser createSqlParser() throws InvocationTargetException, IllegalAccessException {
        Demo demo = createDemo();
        TableParser tableParser = new TableParser(demo.getClass());
        TableInfo tableInfo =  tableParser.parser(demo);
        return new MysqlSqlParser(tableInfo);
    }

    @Test
    void createInsertSql() throws InvocationTargetException, IllegalAccessException {
        MysqlSqlParser mysqlSqlParser = createSqlParser();
        String sql = mysqlSqlParser.createInsertSql();
        System.out.println(sql);
    }



    @Test
    void createSelectAll() throws InvocationTargetException, IllegalAccessException {
        MysqlSqlParser mysqlSqlParser = createSqlParser();
        String sql = mysqlSqlParser.createSelectAll();
        System.out.println(sql);
    }



    @Test
    void createUpdateSql() throws InvocationTargetException, IllegalAccessException {
        MysqlSqlParser mysqlSqlParser = createSqlParser();
        String sql = mysqlSqlParser.createUpdateSql();
        System.out.println(sql);
    }

    @Test
    void createDeleteSql() throws InvocationTargetException, IllegalAccessException {
        MysqlSqlParser mysqlSqlParser = createSqlParser();
        String sql = mysqlSqlParser.createDeleteSql();
        System.out.println(sql);
    }

    @Test
    void createInsertAllSql() throws InvocationTargetException, IllegalAccessException {
        MysqlSqlParser mysqlSqlParser = createSqlParser();
        List<Demo> list = new ArrayList<>();
        list.add(createDemo());
        list.add(createDemo());
        String sql = mysqlSqlParser.createInsertAllSql(list);
        System.out.println(sql);
    }

    @Test
    void createDeleteAllSql() throws InvocationTargetException, IllegalAccessException {
        MysqlSqlParser mysqlSqlParser = createSqlParser();
        String sql = mysqlSqlParser.createDeleteAllSql();
        System.out.println(sql);
    }

    @Test
    void createDeleteAllByIdSql() throws InvocationTargetException, IllegalAccessException {
        MysqlSqlParser mysqlSqlParser = createSqlParser();
        String sql = mysqlSqlParser.createDeleteAllByIdSql();
        System.out.println(sql);
    }

    @Test
    void createGetByIdSql() throws InvocationTargetException, IllegalAccessException {
        MysqlSqlParser mysqlSqlParser = createSqlParser();
        String sql = mysqlSqlParser.createGetByIdSql();
        System.out.println(sql);
    }
}
