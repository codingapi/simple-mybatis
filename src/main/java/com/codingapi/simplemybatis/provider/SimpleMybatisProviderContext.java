package com.codingapi.simplemybatis.provider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lorne
 * @since 1.0.0
 */
public class SimpleMybatisProviderContext {

    private final List<SimpleMybatisSQLBuilder> commandSQLBuilders;

    private SimpleMybatisProviderContext(){
        this.commandSQLBuilders = new ArrayList<>();
    }

    private void addSimpleMybatisSQLBuilder(SimpleMybatisSQLBuilder sqlBuilder){
        this.commandSQLBuilders.add(sqlBuilder);
    }

    private static  SimpleMybatisProviderContext context;

    public static SimpleMybatisProviderContext getInstance(){
        if(context==null){
            synchronized (SimpleMybatisProviderContext.class){
                context = new SimpleMybatisProviderContext();
            }
        }
        return context;
    }

    public void registerSQLBuilder(SimpleMybatisSQLBuilder sqlBuilder){
        this.addSimpleMybatisSQLBuilder(sqlBuilder);
    }

    public <T extends SimpleMybatisSQLBuilder> T getBuilder(Class<T> clazz){
        for(SimpleMybatisSQLBuilder sqlBuilder: commandSQLBuilders){
            if(clazz.isAssignableFrom(sqlBuilder.getClass())){
                return (T)sqlBuilder;
            }
        }
        return null;
    }


}
