package com.codingapi.simplemybatis.query.parser;

import com.codingapi.simplemybatis.query.QueryParameter;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class LikeConditionParser implements IConditionParser {

    @Override
    public QueryCondition type() {
        return QueryCondition.LIKE;
    }

    @Override
    public String condition(QueryParameter queryParameter) {
        StringBuilder currentSql = new StringBuilder();
        currentSql.append(queryParameter.getKey());
        currentSql.append(" like ");
        currentSql.append("'%" + queryParameter.getParamKey(true) + "%'");
        return currentSql.toString();
    }
}
