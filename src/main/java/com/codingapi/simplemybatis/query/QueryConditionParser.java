package com.codingapi.simplemybatis.query;

import com.codingapi.simplemybatis.query.parser.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class QueryConditionParser {

    private List<IConditionParser> conditionParsers = new ArrayList<>();

    public QueryConditionParser() {
        conditionParsers.add(new EqualConditionParser());
        conditionParsers.add(new LikeConditionParser());
        conditionParsers.add(new DateConditionParser());
        conditionParsers.add(new BetweenConditionParser());
        conditionParsers.add(new InConditionParser());
    }

    public String parserParam(QueryParameter queryParameter) {
        queryParameter.getCondition().equals(QueryCondition.SQL);
        for (IConditionParser conditionParser : conditionParsers) {
            if (conditionParser.type().equals(queryParameter.getCondition())) {
                return conditionParser.condition(queryParameter);
            }
        }
        throw new IllegalArgumentException("no find condition parser to " + queryParameter.getCondition());
    }
}
