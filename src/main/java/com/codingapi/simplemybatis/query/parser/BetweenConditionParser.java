package com.codingapi.simplemybatis.query.parser;

import com.codingapi.simplemybatis.query.QueryParameter;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class BetweenConditionParser implements IConditionParser {

    @Override
    public QueryCondition type() {
        return QueryCondition.BETWEEN;
    }

    @Override
    public String condition(QueryParameter queryParameter) {
        StringBuilder currentSql = new StringBuilder();
        currentSql.append(queryParameter.getKey());
        currentSql.append(" between ");
        currentSql.append(queryParameter.getParamKey());
        currentSql.append(" and ");
        currentSql.append(queryParameter.getTwoParamKey());
        return currentSql.toString();
    }
}
