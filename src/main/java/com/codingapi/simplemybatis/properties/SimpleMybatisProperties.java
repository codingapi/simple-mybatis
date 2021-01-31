package com.codingapi.simplemybatis.properties;


public class SimpleMybatisProperties {

    private ColumnNameStyle columnNameStyle = ColumnNameStyle.UNDERLINE;

    public enum ColumnNameStyle {
        CAMEL, UNDERLINE
    }

    public ColumnNameStyle getColumnNameStyle() {
        return columnNameStyle;
    }

    public void setColumnNameStyle(ColumnNameStyle columnNameStyle) {
        this.columnNameStyle = columnNameStyle;
    }
}
