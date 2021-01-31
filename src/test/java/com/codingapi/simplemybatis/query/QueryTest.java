package com.codingapi.simplemybatis.query;

import com.codingapi.simplemybatis.entity.Demo;
import com.codingapi.simplemybatis.parser.SqlParser;
import com.codingapi.simplemybatis.parser.TableInfo;
import com.codingapi.simplemybatis.parser.TableParser;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;

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
        TableInfo tableInfo  = tableParser.parser(demo);
        return new SqlParser(tableInfo);
    }


    @Test
    void query() throws InvocationTargetException, IllegalAccessException {
        SqlParser sqlParser = createSqlParser();
        String sql = sqlParser.createQuery(QueryBuilder.Build()
                .where()
                .condition("name=#{name}","hello")
                .and()
                .condition("create_time=STR_TO_DATE(%{createTime},'%Y-%m-%d')","2020-01-12")
                .or()
                .condition("id in (${ids})", 1,2,3,4,5)
                .groupBy("d.name")
                .builder());
        System.out.println(sql);
    }


    @Test
    void queryView() throws InvocationTargetException, IllegalAccessException {
        Query query = QueryBuilder.Build()
                .select("select * from t_demo d join t_test t on d.id = t.demo_id ")
                .where()
                .condition("d.name = #{name}","name","234")
                .or()
                .condition("d.name = '1'")
                .and()
                .condition("create_time=STR_TO_DATE(%{createTime},'%Y-%m-%d')","createTime","2020-01-12")
                .orderBy("d.name desc")
                .builder();
        SqlBuilder sqlBuilder = new SqlBuilder(query.getSelect(),null,query);
        String sql = sqlBuilder.getSql();
        System.out.println(sql);
    }


    @Test
    void queryNumberKey() throws InvocationTargetException, IllegalAccessException {
        Query query = QueryBuilder.Build()
                .select("select * from t_demo d join t_test t on d.id = t.demo_id ")
                .where()
                .condition("d.order = #{?1} or d.order = #{?2} or d.order = #{?3} or d.order = #{?4} ",1,2,3,4)
                .builder();
        SqlBuilder sqlBuilder = new SqlBuilder(query.getSelect(),null,query);
        String sql = sqlBuilder.getSql();
        System.out.println(sql);
    }

    @Test
    void queryOrderBy() throws InvocationTargetException, IllegalAccessException {
        Query query = QueryBuilder.Build()
                .select("select * from t_demo d join t_test t on d.id = t.demo_id ")
                .orderBy("d.sort desc ")
                .builder();
        SqlBuilder sqlBuilder = new SqlBuilder(query.getSelect(),null,query);
        String sql = sqlBuilder.getSql();
        System.out.println(sql);
    }

    @Test
    void queryLikeKey() throws InvocationTargetException, IllegalAccessException {
        Query query = QueryBuilder.Build()
                .select("select * from t_demo d join t_test t on d.id = t.demo_id ")
                .where()
                .condition("d.name = #{name} and d.name = '123'",new HashMap<>())
                .builder();
        SqlBuilder sqlBuilder = new SqlBuilder(query.getSelect(),null,query);
        String sql = sqlBuilder.getSql();
        System.out.println(sql);
    }

    @Test
    void queryNotNullView() throws InvocationTargetException, IllegalAccessException {
        Object object = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Query query = QueryBuilder.Build()
                .select("select * from t_demo d join t_test t on d.id = t.demo_id ")
                .where()
                .condition("d.name = #{name}","112")
                .and()
                .condition("d.id in (%{ids})",object)
                .builder();
        SqlBuilder sqlBuilder = new SqlBuilder(query.getSelect(),null,query);
        String sql = sqlBuilder.getSql();
        System.out.println(sql);
    }


}
