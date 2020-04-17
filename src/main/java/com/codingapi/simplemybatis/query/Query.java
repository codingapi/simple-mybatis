package com.codingapi.simplemybatis.query;

import com.codingapi.simplemybatis.query.parser.QueryCondition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */

public class Query {

    private List<QueryParameter> parameterList;

    private List<String> conditions;

    private int index = 0;

    private String select;

    private String orderBy;

    public Map<String, Object> data;


    protected Query(String select, List<QueryParameter> parameterList, List<String> conditions, String orderBy) {
        this.select = select;
        this.parameterList = parameterList;
        this.conditions = conditions;
        this.orderBy = orderBy;
        this.data = new HashMap<>();
        pushData();
    }

    public String getSelect() {
        return select;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public List<QueryParameter> getParameterList() {
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
        for (QueryParameter queryParameter : parameterList) {
            if (queryParameter.getCondition().equals(QueryCondition.BETWEEN)) {
                data.put(queryParameter.getKey(), queryParameter.getVal());
                data.put(queryParameter.getTwoKey(), queryParameter.getTwo());
            } else {
                data.put(queryParameter.getKey(), queryParameter.getVal());
            }
        }
    }

}
