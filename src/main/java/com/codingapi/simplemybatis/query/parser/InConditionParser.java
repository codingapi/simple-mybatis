package com.codingapi.simplemybatis.query.parser;

import com.codingapi.simplemybatis.query.QueryParameter;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class InConditionParser implements IConditionParser {

    @Override
    public QueryCondition type() {
        return QueryCondition.IN;
    }

    @Override
    public String condition(QueryParameter queryParameter) {
        StringBuilder currentSql = new StringBuilder();
        currentSql.append(queryParameter.getKey());
        currentSql.append(" in ");
        currentSql.append("<foreach item='item' collection='" + queryParameter.getQueryKey() + "' open='(' separator=',' close=')'>");
        currentSql.append("#{item}");
        currentSql.append("</foreach>");
        return currentSql.toString();
    }
}
