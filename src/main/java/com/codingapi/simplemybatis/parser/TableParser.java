package com.codingapi.simplemybatis.parser;

import com.codingapi.simplemybatis.properties.SimpleMybatisProperties.ColumnNameStyle;
import com.codingapi.simplemybatis.properties.SimpleMybatisPropertiesContext;
import com.codingapi.simplemybatis.utils.StringCharacterUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Class => Table 字段解析
 */
public class TableParser {

    private Class<?> clazz;

    private String tableName;

    private ColumnFiled idColumn;

    private List<ColumnFiled> columns;

    private PropertyDescriptor[] propertyDescriptors;

    private ColumnNameStyle columnNameStyle;

    public TableParser(Class<?> clazz) {
        this.clazz = clazz;
        columnNameStyle = SimpleMybatisPropertiesContext.getInstance().getColumnNameStyle();
        propertyDescriptors = PropertyUtils.getPropertyDescriptors(clazz);
        columns = new ArrayList<>();
        loadTableName();
    }


    public TableInfo parser(Object obj) throws IllegalAccessException, InvocationTargetException {
        loadColumnNames(obj);
        return new TableInfo(tableName, idColumn, columns);
    }

    private void loadColumnNames(Object obj) throws IllegalAccessException, InvocationTargetException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Method method = getFieldMethod(field);
            if(method==null||field.isSynthetic()){
                continue;
            }
            Transient transientField = field.getAnnotation(Transient.class);
            if (transientField != null) {
                continue;
            }
            if (idColumn == null) {
                Id id = field.getAnnotation(Id.class);
                if (id != null) {
                    String idColumnName = getColumnName(field.getAnnotation(Column.class), field);
                    Object idValue = getFieldValue(method, obj);
                    String fieldName = field.getName();
                    idColumn = new ColumnFiled(fieldName, idColumnName, idValue, method);
                    continue;
                }
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = getColumnName(column, field);
            Object value = getFieldValue(method, obj);
            columns.add(new ColumnFiled(field.getName(), columnName, value, method));
        }
    }

    private String getColumnName(Column column, Field field) {
        String columnName;
        if (column == null) {
            columnName = field.getName();
        } else {
            if (StringUtils.isEmpty(column.name())) {
                columnName = field.getName();
            } else {
                return column.name();
            }
        }
        return getColumnStyleName(columnName);
    }

    private String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    private Method getFieldMethod(Field field) {
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String name = field.getName();
            if (field.getType().equals(boolean.class) && field.getName().startsWith("is")) {
                name = name.replaceFirst("is", "");
                name = toLowerCaseFirstOne(name);
            }
            if (name.equals(propertyDescriptor.getName())) {
                return propertyDescriptor.getReadMethod();
            }
        }
        return null;
    }

    private Object getFieldValue(Method method, Object obj)
            throws InvocationTargetException, IllegalAccessException {
        if (obj != null) {
            return method.invoke(obj);
        }
        return null;
    }

    private void loadTableName() {
        Table table = clazz.getAnnotation(Table.class);
        if (table == null) {
            throw new RuntimeException("class " + clazz.getName() + " not @Table annotation.");
        }
        if (StringUtils.isEmpty(table.name())) {
            tableName = getColumnStyleName(table.name());
        } else {
            tableName = table.name();
        }
    }

    private String getColumnStyleName(String name) {
        if (columnNameStyle == ColumnNameStyle.UNDERLINE) {
            return StringCharacterUtils.camelToUnderline(name);
        } else {
            return StringCharacterUtils.underlineToCamel(name);
        }
    }



    public static class ColumnFiled {
        private String fieldName;
        private String columnName;
        private Object value;
        private Method method;

        public ColumnFiled(String fieldName, String columnName, Object value, Method method) {
            this.fieldName = fieldName;
            this.columnName = columnName;
            this.value = value;
            this.method = method;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }
    }


}
