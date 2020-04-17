package com.codingapi.simplemybatis.query.parser;

import com.codingapi.simplemybatis.query.QueryParameter;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public interface IConditionParser {

    QueryCondition type();

    String condition(QueryParameter queryParameter);


}
