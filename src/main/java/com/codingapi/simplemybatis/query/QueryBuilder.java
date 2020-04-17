package com.codingapi.simplemybatis.query;

import com.codingapi.simplemybatis.query.parser.QueryCondition;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class QueryBuilder {

    private Parameter parameter = new Parameter();
    private Condition condition = new Condition();
    private String orderBy;
    private String select;

    public QueryBuilder select(String select) {
        this.select = select;
        return this;
    }

    public class Parameter {
        @Getter
        private List<QueryParameter> parameterList = new ArrayList<>();

        public void column(QueryParameter queryParameter) {
            parameterList.add(queryParameter);
        }

        public void column(String key, Object val, QueryCondition condition) {
            parameterList.add(new QueryParameter(key, val, condition));
        }

        /**
         * 完全匹配
         *
         * @param key
         * @param val
         * @return
         */
        public Condition equal(String key, Object val) {
            parameterList.add(new QueryParameter(key, val, QueryCondition.EQUAL));
            return condition;
        }

        /**
         * 大于匹配
         *
         * @param key
         * @param val
         * @return
         */
        public Condition greater(String key, Object val) {
            parameterList.add(new QueryParameter(key, val, QueryCondition.GREATER));
            return condition;
        }

        /**
         * 小于匹配
         *
         * @param key
         * @param val
         * @return
         */
        public Condition less(String key, Object val) {
            parameterList.add(new QueryParameter(key, val, QueryCondition.LESS));
            return condition;
        }

        /**
         * 模糊匹配
         *
         * @param key
         * @param val
         * @return
         */
        public Condition like(String key, String val) {
            parameterList.add(new QueryParameter(key, val, QueryCondition.LIKE));
            return condition;
        }

        /**
         * 日期字段
         *
         * @param key 数据库字段
         * @param val %Y-%m-%d 格式的时间串 2020-02-17
         * @return
         */
        public Condition date(String key, String val) {
            parameterList.add(new QueryParameter(key, val, QueryCondition.DATE));
            return condition;
        }


        /**
         * in 包含
         *
         * @param key
         * @param val
         * @return
         */
        public Condition in(String key, Object... val) {
            parameterList.add(new QueryParameter(key, Arrays.asList(val), QueryCondition.IN));
            return condition;
        }


    }

    public class Condition {
        @Getter
        private List<String> conditions = new ArrayList<>();

        /**
         * and 条件
         *
         * @return
         */
        public Parameter and() {
            conditions.add(" and ");
            return parameter;
        }

        /**
         * or 条件
         *
         * @return
         */
        public Parameter or() {
            conditions.add(" or ");
            return parameter;
        }

        public QueryBuilder orderBy(String orderByStr) {
            orderBy = orderByStr;
            return QueryBuilder.this;
        }

        public Query builder() {
            return new Query(select, parameter.parameterList, condition.conditions, orderBy);
        }
    }

    public Query builder() {
        return new Query(select, parameter.parameterList, condition.conditions, orderBy);
    }

    public static QueryBuilder Build() {
        QueryBuilder queryBuilder = new QueryBuilder();
        return queryBuilder;
    }

    public Parameter where() {
        return parameter;
    }


}
