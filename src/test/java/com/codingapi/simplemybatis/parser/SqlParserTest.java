package com.codingapi.simplemybatis.parser;

import com.codingapi.simplemybatis.entity.Demo;
import com.codingapi.simplemybatis.provider.builder.BasicSqlParser;
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

    private BasicSqlParser createSqlParser() throws InvocationTargetException, IllegalAccessException {
        Demo demo = createDemo();
        TableParser tableParser = new TableParser(demo.getClass());
        TableInfo tableInfo =  tableParser.parser(demo);
        return new BasicSqlParser(tableInfo);
    }

    @Test
    void createInsertSql() throws InvocationTargetException, IllegalAccessException {
        BasicSqlParser basicSqlParser = createSqlParser();
        String sql = basicSqlParser.createInsertSql();
        System.out.println(sql);
    }



    @Test
    void createSelectAll() throws InvocationTargetException, IllegalAccessException {
        BasicSqlParser basicSqlParser = createSqlParser();
        String sql = basicSqlParser.createSelectAll();
        System.out.println(sql);
    }



    @Test
    void createUpdateSql() throws InvocationTargetException, IllegalAccessException {
        BasicSqlParser basicSqlParser = createSqlParser();
        String sql = basicSqlParser.createUpdateSql();
        System.out.println(sql);
    }

    @Test
    void createDeleteSql() throws InvocationTargetException, IllegalAccessException {
        BasicSqlParser basicSqlParser = createSqlParser();
        String sql = basicSqlParser.createDeleteSql();
        System.out.println(sql);
    }

    @Test
    void createInsertAllSql() throws InvocationTargetException, IllegalAccessException {
        BasicSqlParser basicSqlParser = createSqlParser();
        List<Demo> list = new ArrayList<>();
        list.add(createDemo());
        list.add(createDemo());
        String sql = basicSqlParser.createInsertAllSql(list);
        System.out.println(sql);
    }

    @Test
    void createDeleteAllSql() throws InvocationTargetException, IllegalAccessException {
        BasicSqlParser basicSqlParser = createSqlParser();
        String sql = basicSqlParser.createDeleteAllSql();
        System.out.println(sql);
    }

    @Test
    void createDeleteAllByIdSql() throws InvocationTargetException, IllegalAccessException {
        BasicSqlParser basicSqlParser = createSqlParser();
        String sql = basicSqlParser.createDeleteAllByIdSql();
        System.out.println(sql);
    }

    @Test
    void createGetByIdSql() throws InvocationTargetException, IllegalAccessException {
        BasicSqlParser basicSqlParser = createSqlParser();
        String sql = basicSqlParser.createGetByIdSql();
        System.out.println(sql);
    }
}
