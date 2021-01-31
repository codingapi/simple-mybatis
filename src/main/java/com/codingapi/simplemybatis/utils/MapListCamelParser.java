package com.codingapi.simplemybatis.utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lorne
 * @date 2020/4/28
 * @description
 */
public class MapListCamelParser {

    private List<Map<String,Object>> list;

    private Set<String> keys;

    public MapListCamelParser(List<Map<String, Object>> list) {
        this.keys = new HashSet<>();
        this.list = list;

        if(hasData()){
            camelMap();
        }
    }

    private boolean hasData(){
        return list!=null&&list.size()>0;
    }


    private void camelMap(){
        this.list = list.stream().map(item->{
            MapCamelUtils.camelMap(item);
            keys.addAll(item.keySet());
            return item;
        }).collect(Collectors.toList());
    }

    public List<Map<String,Object>> parser(){
        if(!hasData()) {
            return null;
        }
        return list.stream().map(item -> {
            pushKeys(item);
            return item;
        }).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    private void pushKeys(Map<String,Object> item){
        for(String key:keys){
            if(!item.containsKey(key)){
                item.put(key,null);
            }
        }
    }

}
