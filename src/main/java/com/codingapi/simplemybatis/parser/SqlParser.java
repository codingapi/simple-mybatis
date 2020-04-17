package com.codingapi.simplemybatis.parser;

import com.codingapi.simplemybatis.query.Query;
import com.codingapi.simplemybatis.query.QuerySqlBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SqlParser {

    private TableInfo tableInfo;

    public SqlParser(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String createInsertSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into ");
        stringBuilder.append(tableInfo.getTableName());
        stringBuilder.append("(");
        stringBuilder.append(tableInfo.createColumnName(null));
        stringBuilder.append(")");
        stringBuilder.append("values(");
        stringBuilder.append(tableInfo.createFieldName(null, null));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public String createSelectAll() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        stringBuilder.append(tableInfo.columnToFiled());
        stringBuilder.append(" from ");
        stringBuilder.append(tableInfo.getTableName());
        return stringBuilder.toString();
    }

    public String createQuery(Query query) {
        QuerySqlBuilder querySqlBuilder = new QuerySqlBuilder(tableInfo.columnToFiled(), tableInfo.getTableName(), query);
        return querySqlBuilder.getSql();
    }


    public String createUpdateSql() {
        if (tableInfo.getIdColumnFiled().getValue() == null) {
            throw new RuntimeException("no Id value,do't create update sql.");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("update ");
        stringBuilder.append(tableInfo.getTableName());
        stringBuilder.append(tableInfo.createSetColumn());
        stringBuilder.append(" where ");
        stringBuilder.append(tableInfo.getIdColumnFiled().getColumnName());
        stringBuilder.append("=");
        stringBuilder.append("#{").append(tableInfo.getIdColumnFiled().getFieldName()).append("}");
        return stringBuilder.toString();
    }

    public String createDeleteSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("delete from ");
        stringBuilder.append(tableInfo.getTableName());
        stringBuilder.append(" where ");
        stringBuilder.append(tableInfo.getIdColumnFiled().getColumnName());
        stringBuilder.append("=");
        stringBuilder.append("#{").append(tableInfo.getIdColumnFiled().getFieldName()).append("}");
        return stringBuilder.toString();
    }


    public String createInsertAllSql(List list)
            throws InvocationTargetException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<script>");
        stringBuilder.append("insert into ");
        stringBuilder.append(tableInfo.getTableName());
        stringBuilder.append("(");
        Object firstObj = list.get(0);
        Object idValue = tableInfo.getIdColumnFiled().getMethod().invoke(firstObj);
        stringBuilder.append(tableInfo.createColumnName(idValue));
        stringBuilder.append(") ");
        stringBuilder.append("values ");
        stringBuilder.append("<foreach item='item' collection='list'  separator=','>");
        stringBuilder.append(" ( ");
        stringBuilder.append(tableInfo.createFieldName(idValue, "item"));
        stringBuilder.append(" ) ");
        stringBuilder.append("</foreach>");
        stringBuilder.append("</script>");
        return stringBuilder.toString();
    }

    public String createDeleteAllSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<script>");
        stringBuilder.append("delete from ");
        stringBuilder.append(tableInfo.getTableName());
        stringBuilder.append(" where ");
        stringBuilder.append(tableInfo.getIdColumnFiled().getColumnName());
        stringBuilder.append(" in ");
        stringBuilder.append(" ( ");
        stringBuilder.append("<foreach item='item' collection='list'  separator=','>");
        stringBuilder.append("#{item.");
        stringBuilder.append(tableInfo.getIdColumnFiled().getFieldName());
        stringBuilder.append("}");
        stringBuilder.append("</foreach>");
        stringBuilder.append(" ) ");
        stringBuilder.append("</script>");
        return stringBuilder.toString();
    }

    public String createDeleteAllByIdSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<script>");
        stringBuilder.append("delete from ");
        stringBuilder.append(tableInfo.getTableName());
        stringBuilder.append(" where ");
        stringBuilder.append(tableInfo.getIdColumnFiled().getColumnName());
        stringBuilder.append(" in ");
        stringBuilder.append(" ( ");
        stringBuilder.append("<foreach item='item' collection='list'  separator=','>");
        stringBuilder.append("#{item}");
        stringBuilder.append("</foreach>");
        stringBuilder.append(" ) ");
        stringBuilder.append("</script>");
        return stringBuilder.toString();
    }

    public String createGetByIdSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        stringBuilder.append(tableInfo.columnToFiled());
        stringBuilder.append(" from ");
        stringBuilder.append(tableInfo.getTableName());
        stringBuilder.append(" where ");
        stringBuilder.append(tableInfo.getIdColumnFiled().getColumnName());
        stringBuilder.append(" = ");
        stringBuilder.append("#{id}");
        return stringBuilder.toString();
    }

}
