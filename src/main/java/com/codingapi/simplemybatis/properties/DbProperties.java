package com.codingapi.simplemybatis.properties;

import lombok.Data;

@Data
public class DbProperties {

    private ColumnNameStyle columnNameStyle = ColumnNameStyle.UNDERLINE;

    public enum ColumnNameStyle {
        CAMEL, UNDERLINE
    }

}
