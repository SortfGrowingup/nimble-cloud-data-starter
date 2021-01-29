package com.example.demo.spec;

import javax.persistence.criteria.*;

/**
 * @author SortfGrowingup
 * @version 1.0
 * @date 2021/1/26 15:17
 */
public class EqualSpecification<T> extends AbstractSpec<T>{
    private  String property;
    private final transient String predicateJSON;
    private final transient Object[] values;
    public EqualSpecification(String predicateJSON, Object[] values) {
        this.predicateJSON = predicateJSON;
        this.values = values;
    }

    @Override
    public Predicate toPredicate(Root<T> r, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        From from = getRoot(property, r);
        String field = getProperty(property);
        if (values == null) {
            return cb.isNull(from.get(field));
        }
        if (values.length == 1) {
            return getPredicate(from, cb, values[0], field);
        }

        Predicate[] predicates = new Predicate[values.length];
        for (int i = 0; i < values.length; i++) {
            predicates[i] = getPredicate(r, cb, values[i], field);
        }
        return cb.or(predicates);
    }

    private Predicate getPredicate(From root, CriteriaBuilder cb, Object value, String field) {
        return value == null ? cb.isNull(root.get(field)) : cb.equal(root.get(field), value);
    }
}