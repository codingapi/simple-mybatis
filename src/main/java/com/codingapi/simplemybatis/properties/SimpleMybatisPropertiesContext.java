package com.codingapi.simplemybatis.properties;


import com.codingapi.simplemybatis.properties.SimpleMybatisProperties.ColumnNameStyle;

public class SimpleMybatisPropertiesContext {

    private static SimpleMybatisPropertiesContext instance;

    public static SimpleMybatisPropertiesContext getInstance() {
        if(instance==null){
            synchronized (SimpleMybatisPropertiesContext.class){
                instance = new SimpleMybatisPropertiesContext();
            }
        }
        return instance;
    }

    private final SimpleMybatisProperties simpleMybatisProperties;

    private SimpleMybatisPropertiesContext() {
        simpleMybatisProperties = new SimpleMybatisProperties();
    }

    public void setColumnNameStyle(ColumnNameStyle columnNameStyle) {
        simpleMybatisProperties.setColumnNameStyle(columnNameStyle);
    }

    public ColumnNameStyle getColumnNameStyle() {
        return simpleMybatisProperties.getColumnNameStyle();
    }


}
