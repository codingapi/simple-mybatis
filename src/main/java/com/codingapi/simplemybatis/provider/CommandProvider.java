package com.codingapi.simplemybatis.provider;

import com.codingapi.simplemybatis.parser.SqlParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class CommandProvider extends BaseProvider {


    public String save(Object obj, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SqlParser sqlParser = createParser(obj, context);
        return sqlParser.createInsertSql();
    }

    public String saveAll(Map<String, Object> map, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        List list = (List) map.get("list");
        if (list == null || list.size() == 0) {
            throw new RuntimeException("not data.");
        }
        SqlParser sqlParser = createParser(null, context);
        return sqlParser.createInsertAllSql(list);
    }

    public String deleteAll(Map<String, Object> map, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SqlParser sqlParser = createParser(null, context);
        return sqlParser.createDeleteAllSql();
    }

    public String deleteAllById(Map<String, Object> map, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SqlParser sqlParser = createParser(null, context);
        return sqlParser.createDeleteAllByIdSql();
    }


    public String update(Object obj, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SqlParser sqlParser = createParser(obj, context);
        return sqlParser.createUpdateSql();
    }

    public String delete(Object obj, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SqlParser sqlParser = createParser(obj, context);
        return sqlParser.createDeleteSql();
    }

    public String deleteById(Object obj, ProviderContext context) throws IllegalAccessException, InvocationTargetException {
        SqlParser sqlParser = createParser(null, context);
        return sqlParser.createDeleteSql();
    }


}
