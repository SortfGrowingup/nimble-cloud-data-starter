package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.E;
import com.example.demo.spec.EqualSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.*;

import static javax.persistence.criteria.Predicate.BooleanOperator.*;

/**
 * @author SortfGrowingup
 * @version 1.0
 */
public class SpecTools {

    private SpecTools(){}

    public static <T> Spec<T> and() {
        return new Spec<>(AND);
    }
    public static <T> Spec<T> or() {
        return new Spec<>(OR);
    }

    public static class Spec<T> {
        private final Predicate.BooleanOperator operator;
        private List<Specification<T>> specifications;

        public Spec(Predicate.BooleanOperator operator) {
            this.operator = operator;
            this.specifications = new ArrayList<>();
        }

        public Spec<T> eq(String property, Object... values) {
            return eq(true, property, values);
        }

        public Spec<T> eq(boolean condition, String property, Object... values) {
            return this.predicate(condition, new EqualSpecification<>(property, values));
        }

        public Spec<T> predicate(Specification<T> specification) {
            return predicate(true, specification);
        }

        public Spec<T> predicate(boolean condition, Specification<T> specification) {
            if (condition) {
                this.specifications.add(specification);
            }
            return this;
        }
        public Specification<T> build() {
            return (Root<T> r, CriteriaQuery<?> q, CriteriaBuilder cb) -> {
                Predicate[] predicates = new Predicate[specifications.size()];

                for (int i = 0; i < specifications.size(); i++) {
                    predicates[i] = specifications.get(i).toPredicate(r, q, cb);
                }
                if (Objects.equals(predicates.length, 0)) return null;
                return OR.equals(operator) ? cb.or(predicates) : cb.and(predicates);
            };
        }
    }

    public static Map<String, Map<String, String>> SUPPER_REGION = new HashMap<>();
    public static final Map<String, Object> QUERY_ENTITY = new HashMap<>();
    public static final String WHERE_C = "condition";
    public static final String WHERE_F = "field";
    public static final String WHERE_V = "value";
    static {
        QUERY_ENTITY.put("E", E.class);
    }
    public static String T = "{\"query\":\"E\",\"eq\":{\"name\":[\"小1\",\"小2\"],\"createOper\":[\"超级管理员\"]}}";
   // public Map<String, Object>
    public static void main(String[] args) {
        JSONObject source = JSONObject.parseObject(T);
        String query = (String) source.get("query");
        System.out.println(query);
    }

    private static void toWhere(Root<E> root, CriteriaBuilder b, JSONObject item){
        String condition = (String)item.get(WHERE_C);
        String field = (String)item.get(WHERE_F);
        String value = (String)item.get(WHERE_V);
        int t = 0;
        Specification<E> build = SpecTools.<E>and().build();
        switch (condition){
            case "大于" :
                if (StringUtils.isNotBlank(value)) t = Integer.parseInt(value);
                b.gt(root.get(field), t);break;
            case "大于等于" :
                if (StringUtils.isNotBlank(value)) t = Integer.parseInt(value);
                b.ge(root.get(field), t);break;
            case "小于" :
                if (StringUtils.isNotBlank(value)) t = Integer.parseInt(value);
                b.lt(root.get(field), t);break;
            case "小于等于" :
                if (StringUtils.isNotBlank(value)) t = Integer.parseInt(value);
                b.le(root.get(field), t);break;
            case "等于" : b.equal(root.get(field), value);break;
            case "不等于" : b.notEqual(root.get(field), value);break;
            default: break;
        }
    }
    public static Specification<E> getSpecification(){
        return (Specification<E>) (r, q, cb) -> {
            System.out.println("r->\n\b"+r +
                    "\nquery->\n\b"+q );
            Predicate p1 = cb.equal(r.get("uuid"),"acdb5857-9b5d-4cd0-82a9-31403c360734");
            cb.ge(r.get(""), 20);
            //将两个查询条件联合起来之后返回Predicate对象
            return cb.and(p1);
        };

        /*
        gt     大与
        lt     小于
        equal  等于
        */
    }
}