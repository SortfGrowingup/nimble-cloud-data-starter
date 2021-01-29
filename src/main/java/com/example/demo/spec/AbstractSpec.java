package com.example.demo.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SortfGrowingup
 * @version 1.0
 * @date 2021/1/25 12:04
 */
public abstract class AbstractSpec<T> implements Specification<T>, Serializable {
    private final transient Map<String, Object[]> predicateMap = new HashMap<>();
    public Map<String, Object[]> getPredicateMap(){
        return predicateMap;
    }

    public Map<String, Object[]> put(String k, Object[] v){
        predicateMap.put(k, v);
        return predicateMap;
    }

    public String getProperty(String property) {
        if (property.contains(".")) {
            return StringUtils.split(property, ".")[1];
        }
        return property;
    }

    public From getRoot(String p, Root<T> r) {
        if (p.contains(".")) {
            String joinProperty = StringUtils.split(p, ".")[0];
            return r.join(joinProperty, JoinType.LEFT);
        }
        return r;
    }
}