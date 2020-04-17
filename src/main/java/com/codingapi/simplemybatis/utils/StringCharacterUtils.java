package com.codingapi.simplemybatis.utils;

import com.google.common.base.CaseFormat;

public class StringCharacterUtils {

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String camelToUnderline(String str) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }

    /**
     * 下划线 转 驼峰
     *
     * @param str
     * @return
     */
    public static String underlineToCamel(String str) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }

}
