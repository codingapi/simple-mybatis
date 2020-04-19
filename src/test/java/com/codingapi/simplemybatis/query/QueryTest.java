package com.codingapi.simplemybatis.query;

import com.codingapi.simplemybatis.entity.Demo;
import com.codingapi.simplemybatis.parser.SqlParser;
import com.codingapi.simplemybatis.parser.TableParser;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

class QueryTest {


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
    void query() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        String sql = sqlParser.createQuery(QueryBuilder.Build()
                .where()
                .equal("name","hello")
                .and()
                .date("create_time","2020-01-12")
                .or()
                .in("id",1,2,3,4,5)
                .orderBy("name desc")
                .builder());
        System.out.println(sql);
    }


    @Test
    void queryView() throws InvocationTargetException, IllegalAccessException {
        Query query = QueryBuilder.Build()
                .select("select * from t_demo d join t_test t on d.id = t.demo_id ")
                .where()
                .equal("d.name","hello")
                .and()
                .date("d.create_time","2020-01-12")
                .or()
                .in("d.id",1,2,3,4,5)
                .orderBy("d.name desc")
                .builder();
        QuerySqlBuilder querySqlBuilder = new QuerySqlBuilder(query.getSelect(),null,query);
        String sql = querySqlBuilder.getSql();
        System.out.println(sql);
    }

}