package com.codingapi.simplemybatis.provider;

import com.codingapi.simplemybatis.entity.Demo;
import com.codingapi.simplemybatis.provider.mysql.MysqlSqlParser;
import com.codingapi.simplemybatis.parser.TableInfo;
import com.codingapi.simplemybatis.parser.TableParser;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lorne
 * @since 1.0.0
 */
class AutoBuilderProviderTest {

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
    void create() throws InvocationTargetException, IllegalAccessException {
        MysqlSqlParser mysqlSqlParser = createSqlParser();
        String sql = mysqlSqlParser.createInsertSql();
        System.out.println(sql);
    }


}
