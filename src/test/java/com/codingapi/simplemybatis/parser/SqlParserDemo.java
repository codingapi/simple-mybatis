package com.codingapi.simplemybatis.parser;

import com.codingapi.simplemybatis.entity.Demo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;


public class SqlParserDemo {

    @Test
    public void save() throws InvocationTargetException, IllegalAccessException {
        Demo demo = new Demo();
        demo.setName("test");
        demo.setFlag(false);
        demo.setTest("123");

        TableParser tableParser = new TableParser(demo.getClass());
        tableParser.parser(demo);
        SqlParser sqlParser = new SqlParser(tableParser.getTableInfo());
        String sql = sqlParser.createInsertSql();
        System.out.println(sql);

    }
}
