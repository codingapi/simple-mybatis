package com.codingapi.simplemybatis.properties;


import com.codingapi.simplemybatis.properties.DbProperties.ColumnNameStyle;

public class GlabelProperties {

    private static GlabelProperties instance = new GlabelProperties();

    private GlabelProperties() {
        dbProperties = new DbProperties();
    }

    private DbProperties dbProperties;

    public static GlabelProperties getInstance() {
        return instance;
    }

    public ColumnNameStyle getColumnNameStyle() {
        return dbProperties.getColumnNameStyle();
    }


}
