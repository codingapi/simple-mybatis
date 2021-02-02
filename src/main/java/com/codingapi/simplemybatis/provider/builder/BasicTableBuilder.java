package com.codingapi.simplemybatis.provider.builder;

import com.codingapi.simplemybatis.parser.TableInfo;
import com.codingapi.simplemybatis.parser.TableParser;

/**
 * @author lorne
 * @since 1.0.0
 */
public class BasicTableBuilder {

    private StringBuilder builder;

    private TableInfo tableInfo;

    public BasicTableBuilder(TableInfo tableInfo) {
        this.builder = new StringBuilder();
        this.tableInfo = tableInfo;
    }

    public void builder(){
        builder.append("create table ");
        builder.append(tableInfo.getTableName());
        builder.append(" (");
        this.id();
        this.columns();
        builder.deleteCharAt(builder.length()-1);
        builder.append(" )");
    }

    public String sql(){
        return builder.toString().toUpperCase();
    }

    private void id(){
        if(tableInfo.getIdColumnFiled()!=null){
            TableParser.ColumnFiled columnFiled = tableInfo.getIdColumnFiled();
            builder.append(columnFiled.getColumnName());
            builder.append(" ");
            builder.append(columnFiled.getColumnDefinition());
            builder.append(" primary key ,");
        }
    }

    private void columns(){
        for(TableParser.ColumnFiled columnFiled : tableInfo.getColumnFields()) {
            builder.append(columnFiled.getColumnName());
            builder.append(" ");
            builder.append(columnFiled.getColumnDefinition());
            builder.append(" ,");
        }
    }
}
