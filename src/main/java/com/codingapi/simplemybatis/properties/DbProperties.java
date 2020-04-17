package com.codingapi.simplemybatis.properties;


public class DbProperties {

    private ColumnNameStyle columnNameStyle = ColumnNameStyle.UNDERLINE;

    public enum ColumnNameStyle {
        CAMEL, UNDERLINE
    }

    public ColumnNameStyle getColumnNameStyle() {
        return columnNameStyle;
    }
}
