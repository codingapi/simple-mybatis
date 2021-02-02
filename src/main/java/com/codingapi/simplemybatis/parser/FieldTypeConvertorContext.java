package com.codingapi.simplemybatis.parser;


import java.util.HashMap;
import java.util.Map;

/**
 * @author lorne
 * @since 1.0.0
 */
public class FieldTypeConvertorContext {

    private static FieldTypeConvertorContext context;

    public static FieldTypeConvertorContext getInstance(){
        if(context==null){
            synchronized (FieldTypeConvertorContext.class){
                context = new FieldTypeConvertorContext();
            }
        }
        return context;
    }

    private final Map<Class<?>,String> convertors;

    private FieldTypeConvertorContext(){
        this.convertors = new HashMap<>();
        this.init();
    }

    private void init(){
        addConvertor(String.class,"varchar(%d)");
        addConvertor(Integer.class,"int(%d)");
        addConvertor(int.class,"int(%d)");
        addConvertor(long.class,"int(%d)");
        addConvertor(Long.class,"int(%d)");
        addConvertor(boolean.class,"int(1)");
        addConvertor(Boolean.class,"int(1)");
    }

    public void addConvertor(Class<?> key,String value){
        convertors.put(key, value);
    }


    public String convertor(Class<?> type,int length){
        String format =  convertors.get(type);
        if(format==null){
            throw new RuntimeException("cont find convertor match type:"+type);
        }
        return String.format(format,length);
    }

}
