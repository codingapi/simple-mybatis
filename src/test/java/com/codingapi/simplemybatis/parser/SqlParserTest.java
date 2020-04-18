package com.codingapi.simplemybatis.parser;

import com.codingapi.simplemybatis.entity.Demo;
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

    private SqlParser createSqlParser() throws InvocationTargetException, IllegalAccessException {
        Demo demo = createDemo();
        TableParser tableParser = new TableParser(demo.getClass());
        tableParser.parser(demo);
        return new SqlParser(tableParser.getTableInfo());
    }

    @Test
    void createInsertSql() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        String sql = sqlParser.createInsertSql();
        System.out.println(sql);
    }



    @Test
    void createSelectAll() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        String sql = sqlParser.createSelectAll();
        System.out.println(sql);
    }



    @Test
    void createUpdateSql() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        String sql = sqlParser.createUpdateSql();
        System.out.println(sql);
    }

    @Test
    void createDeleteSql() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        String sql = sqlParser.createDeleteSql();
        System.out.println(sql);
    }

    @Test
    void createInsertAllSql() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        List<Demo> list = new ArrayList<>();
        list.add(createDemo());
        list.add(createDemo());
        String sql = sqlParser.createInsertAllSql(list);
        System.out.println(sql);
    }

    @Test
    void createDeleteAllSql() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        String sql = sqlParser.createDeleteAllSql();
        System.out.println(sql);
    }

    @Test
    void createDeleteAllByIdSql() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        String sql = sqlParser.createDeleteAllByIdSql();
        System.out.println(sql);
    }

    @Test
    void createGetByIdSql() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        String sql = sqlParser.createGetByIdSql();
        System.out.println(sql);
    }
}