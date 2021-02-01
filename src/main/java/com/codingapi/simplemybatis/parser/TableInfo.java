package com.codingapi.simplemybatis.parser;


import java.util.List;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class TableInfo {

    private String tableName;

    private TableParser.ColumnFiled idColumnFiled;

    private List<TableParser.ColumnFiled> columnFields;

    public TableInfo(String tableName,
                     TableParser.ColumnFiled idColumnFiled,
                     List<TableParser.ColumnFiled> columnFields) {
        this.tableName = tableName;
        this.idColumnFiled = idColumnFiled;
        this.columnFields = columnFields;
    }

    public TableParser.ColumnFiled getIdColumnFiled() {
        return idColumnFiled;
    }

    public List<TableParser.ColumnFiled> getColumnFields() {
        return columnFields;
    }

    public String getTableName() {
        return tableName;
    }

    public String createColumnName(Object idValue) {
        StringBuilder stringBuilder = new StringBuilder();
        if (getIdColumnFiled().getValue() != null || idValue != null) {
            stringBuilder.append(getIdColumnFiled().getColumnName()).append(",");
        }
        for (TableParser.ColumnFiled columnFiled : getColumnFields()) {
            stringBuilder.append(columnFiled.getColumnName()).append(",");
        }
        String sql = stringBuilder.toString();
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1);
        }
        return sql;
    }

    public String createSetColumn() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" set ");
        for (TableParser.ColumnFiled columnFiled : getColumnFields()) {
            if (columnFiled.getValue() != null) {
                stringBuilder.append(columnFiled.getColumnName()).append("= #{").append(columnFiled.getFieldName()).append("},");
            }
        }
        String sql = stringBuilder.toString();
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1);
        }
        return sql;
    }

    public String createFieldName(Object idValue, String item) {
        StringBuilder stringBuilder = new StringBuilder();
        if (getIdColumnFiled().getValue() != null || idValue != null) {
            if (item != null) {
                stringBuilder.append("#{").append(item).append(".").append(getIdColumnFiled().getColumnName())
                        .append("},");
            } else {
                stringBuilder.append("#{").append(getIdColumnFiled().getColumnName())
                        .append("},");
            }
        }
        for (TableParser.ColumnFiled columnFiled : getColumnFields()) {
            if (item != null) {
                stringBuilder.append("#{").append(item).append(".").append(columnFiled.getFieldName()).append("},");
            } else {
                stringBuilder.append("#{").append(columnFiled.getFieldName()).append("},");
            }
        }
        String sql = stringBuilder.toString();
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1);
        }
        return sql;
    }


    public String columnToFiled() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getIdColumnFiled().getColumnName());
        stringBuilder.append(" as ");
        stringBuilder.append(getIdColumnFiled().getFieldName());
        stringBuilder.append(",");
        for (TableParser.ColumnFiled columnFiled : getColumnFields()) {
            stringBuilder.append(columnFiled.getColumnName()).append(" as ").append(columnFiled.getFieldName()).append(",");
        }
        String sql = stringBuilder.toString();
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1);
        }
        return sql;
    }

}
