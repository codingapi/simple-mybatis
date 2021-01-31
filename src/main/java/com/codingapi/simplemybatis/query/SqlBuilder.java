package com.codingapi.simplemybatis.query;


import org.apache.commons.lang3.StringUtils;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class SqlBuilder {

    private StringBuilder currentSql;
    private Query query;
    private String tableName;
    private String columnName;

    public SqlBuilder(String columnName, String tableName, Query query) {
        this.currentSql = new StringBuilder();
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
        for (ConditionParameter conditionParameter : query.getParameterList()) {
            currentSql.append(conditionParameter.getSql());
            currentSql.append(query.getCondition());
        }
    }

    private void bySql() {
        if (StringUtils.isNotEmpty(query.getBySql())) {
            currentSql.append(query.getBySql());
        }
    }

    public String getSql() {
        appendBegin();
        if (hasParameter()) {
            appendWhere();
            appendParameter();
            appendOver();
        }
        bySql();
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
