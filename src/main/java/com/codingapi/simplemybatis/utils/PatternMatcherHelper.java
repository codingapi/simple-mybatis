package com.codingapi.simplemybatis.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherHelper {

    public final static String BRACE_MATCHER = "\\{([^}]*)\\}";

    private Pattern pattern;

    public PatternMatcherHelper(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public String matcher(String val){
        Matcher matcher =  pattern.matcher(val);
        if (matcher.find()){
            return matcher.group(1);
        }
        throw new IllegalArgumentException(String.format("not matcher of {%s} in regex:%s",val,pattern.pattern()));
    }

    public List<String> matcherList(String val){
        List<String> list = new ArrayList<>();
        Matcher matcher =  pattern.matcher(val);
        while (matcher.find()){
            list.add(matcher.group(1));
        }
        return list;
    }

}
