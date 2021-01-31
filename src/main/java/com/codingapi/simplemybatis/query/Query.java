package com.codingapi.simplemybatis.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */

public class Query {

    private List<ConditionParameter> parameterList;

    private List<String> conditions;

    private int index = 0;

    private String select;

    private String bySql;

    public Map<String, Object> data;


    protected Query(String select, List<ConditionParameter> parameterList, List<String> conditions, String bySql) {
        this.select = select;
        this.parameterList = parameterList;
        this.conditions = conditions;
        this.bySql = bySql;
        this.data = new HashMap<>();
        pushData();
    }

    public String getSelect() {
        return select;
    }

    public String getBySql() {
        return bySql;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public List<ConditionParameter> getParameterList() {
        return parameterList;
    }

    public String getCondition() {
        try {
            return conditions.get(index++);
        } catch (Exception e) {
            return " ";
        }
    }

    private void pushData() {
        for (ConditionParameter parameter : parameterList) {
            Set<String> keys = parameter.keys();
            for (String key : keys) {
                data.put(key, parameter.getVal(key));
            }
        }
    }

}
