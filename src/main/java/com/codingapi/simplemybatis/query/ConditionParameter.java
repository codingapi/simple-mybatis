package com.codingapi.simplemybatis.query;

import com.codingapi.simplemybatis.utils.PatternMatcherHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class ConditionParameter {

    private String sql;
    private Map<String,Object> map;

    public ConditionParameter(String sql) {
        this.map = new HashMap<>();
        this.sql = sql;
    }

    public ConditionParameter(String key, Object val, String sql) {
        this.map = new HashMap<>();
        this.sql = sql;
        map.put(key,val);
    }

    public ConditionParameter(Map<String,Object> map, String sql) {
        this.map = map;
        this.sql = sql;
    }


    public ConditionParameter(Object val, String sql) {
        this.map = new HashMap<>();
        String key = new PatternMatcherHelper(PatternMatcherHelper.BRACE_MATCHER).matcher(sql);
        map.put(key,val);
        this.sql = sql;
    }


    public String getSql() {
        for(String key:keys()) {
            sql = sql.replace(String.format("{%s}", key), String.format("{%s}", getQueryParamKey(key)));
        }
        return sql;
    }

    public Object getVal(String key) {
        return map.get(key);
    }

    public Set<String> keys() {
        return map.keySet();
    }

    private String getQueryParamKey(String key) {
        return String.format("query.data.%s", key);
    }


}
