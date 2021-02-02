package com.codingapi.simplemybatis.parser;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author lorne
 * @since 1.0.0
 */
class FieldTypeConvertorContextTest {


    static class  Demo{
        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    @Test
    void convertor() {
        Field[] fields = Demo.class.getDeclaredFields();
        for(Field field:fields){
            String res = FieldTypeConvertorContext.getInstance().convertor(field.getType(),255);
            System.out.println("type:"+field.getType()+",res:"+res);
        }

    }
}
