package com.codingapi.simplemybatis.query;

import com.codingapi.simplemybatis.query.parser.QueryCondition;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class QueryParameter {

    public QueryParameter(String key, Object val, QueryCondition condition) {
        this.key = key;
        this.val = val;
        this.condition = condition;
    }

    private String key;
    private Object val;
    private Object two;
    private QueryCondition condition;

    public String getKey() {
        return key;
    }

    public Object getVal() {
        return val;
    }

    public Object getTwo() {
        return two;
    }

    public QueryCondition getCondition() {
        return condition;
    }

    public String getParamKey() {
        return getParamKey(false);
    }

    public String getParamKey(boolean dollar) {
        if (dollar) {
            return String.format("${%s}", getQueryKey());
        } else {
            return String.format("#{%s}", getQueryKey());
        }
    }

    public String getQueryKey() {
        return String.format("query.data.%s", key);
    }

    public String getTwoParamKey() {
        return String.format("#{query.data.%s}", getTwoKey());
    }

    public String getTwoKey() {
        return String.format("%s_two", key);
    }
}
