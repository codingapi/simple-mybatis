package com.codingapi.simplemybatis.query;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lorne
 * @date 2020/4/15
 * @description
 */
public class QueryBuilder {

    private Condition condition = new Condition();
    private Splice splice = new Splice();
    private String bySql;
    private String select;

    public QueryBuilder select(String select) {
        this.select = select;
        return this;
    }

    public class Condition {
        private List<ConditionParameter> conditions = new ArrayList<>();

        public Splice condition(String conditionSql,String paramKey, Object paramVal) {
            if(paramVal!=null) {
                conditions.add(new ConditionParameter(paramKey, paramVal, conditionSql));
            }else{
                splice.remove();
            }
            return splice;
        }


        public Splice condition(String conditionSql,Map<String,Object> map) {
            if(map!=null&&map.size()>0) {
                conditions.add(new ConditionParameter(map, conditionSql));
            }else{
                splice.remove();
            }
            return splice;
        }


        public Splice condition(String conditionSql,Object paramVal) {
            if(paramVal!=null) {
                conditions.add(new ConditionParameter(paramVal, conditionSql));
            }else{
                splice.remove();
            }
            return splice;
        }


        public Splice condition(String conditionSql,Object... paramVal) {
            if(paramVal!=null&&paramVal.length>0) {
                conditions.add(new ConditionParameter(StringUtils.join(paramVal, ","), conditionSql));
            }else{
                splice.remove();
            }
            return splice;
        }

    }

    public class Splice {
        private List<String> splices = new ArrayList<>();
        /**
         * and 条件
         *
         * @return
         */
        public Condition and() {
            splices.add(" and ");
            return condition;
        }

        /**
         * or 条件
         *
         * @return
         */
        public Condition or() {
            splices.add(" or ");
            return condition;
        }

        public Condition splice(String splice) {
            splices.add(String.format(" %s ",splice));
            return condition;
        }



        public QueryBuilder orderBy(String orderByStr) {
            bySql = String.format(" order by %s",orderByStr);
            return QueryBuilder.this;
        }

        public QueryBuilder groupBy(String groupBySql) {
            bySql = String.format(" group by %s",groupBySql);
            return QueryBuilder.this;
        }

        public Query builder() {
            return new Query(select, condition.conditions, splice.splices, bySql);
        }

        public void remove() {
            if(splices.size()>0) {
                splices.remove(splices.size()-1);
            }
        }
    }

    public Query builder() {
        return new Query(select, condition.conditions, splice.splices, bySql);
    }

    public static QueryBuilder Build() {
        return new QueryBuilder();
    }

    public Condition where() {
        return condition;
    }

    public QueryBuilder orderBy(String orderByStr) {
        bySql = String.format(" order by %s",orderByStr);
        return QueryBuilder.this;
    }

}
