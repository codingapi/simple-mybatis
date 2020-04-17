package com.codingapi.simplemybatis.query;

import org.springframework.util.StringUtils;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class QuerySqlBuilder {

    private StringBuilder currentSql;
    private Query query;
    private QueryConditionParser queryConditionParser = new QueryConditionParser();
    private String tableName;
    private String columnName;

    public QuerySqlBuilder(String columnName, String tableName, Query query) {
        this.currentSql = new StringBuilder();
        ;
        this.query = query;
        this.tableName = tableName;
        this.columnName = columnName;
    }

    private void appendWhere() {
        if (query != null) {
            currentSql.append(" where ");
        }
    }

    private void appendOver() {
        if (query != null) {
            currentSql.append(" and 1=1 ");
        }
    }

    private boolean hasParameter() {
        return query.getParameterList() != null && query.getParameterList().size() > 0;
    }

    private void appendParameter() {
        for (QueryParameter queryParameter : query.getParameterList()) {
            currentSql.append(queryConditionParser.parserParam(queryParameter));
            currentSql.append(query.getCondition());
        }
    }

    private void orderBy() {
        if (!StringUtils.isEmpty(query.getOrderBy())) {
            currentSql.append(" order by ");
            currentSql.append(query.getOrderBy());
        }
    }

    public String getSql() {
        appendBegin();
        if (hasParameter()) {
            appendWhere();
            appendParameter();
            appendOver();
        }
        orderBy();
        currentSql.append("</script>");
        return currentSql.toString();
    }

    private boolean hasSelect() {
        return query.getSelect() != null && !"".equals(query.getSelect());
    }

    private void appendBegin() {
        currentSql.append("<script>");
        if (hasSelect()) {
            currentSql.append(query.getSelect());
        } else {
            currentSql.append("select ");
            currentSql.append(columnName);
            currentSql.append(" from ");
            currentSql.append(tableName);
        }

    }

}
