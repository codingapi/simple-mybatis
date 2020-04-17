package com.codingapi.simplemybatis.query.parser;

import com.codingapi.simplemybatis.query.QueryParameter;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class DateConditionParser implements IConditionParser {

    @Override
    public QueryCondition type() {
        return QueryCondition.DATE;
    }

    @Override
    public String condition(QueryParameter queryParameter) {
        StringBuilder currentSql = new StringBuilder();
        currentSql.append(queryParameter.getKey());
        currentSql.append(" = ");
        currentSql.append("STR_TO_DATE(" + queryParameter.getParamKey() + ",'%Y-%m-%d')");
        return currentSql.toString();
    }
}
