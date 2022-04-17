package com.legermano.allevents.util.genericSearch;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Getter;

@Getter
public class GenericSpecification<T> implements Specification<T>{

    private SpecificationSearchCriteria criteria;

    public GenericSpecification(SpecificationSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.<Date>get(criteria.getKey()), builder.parameter(Date.class, criteria.getValue().toString()));
            case LESS_THAN:
                return builder.lessThan(root.<Date>get(criteria.getKey()), builder.parameter(Date.class, criteria.getValue().toString()));
            case LIKE:
                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            default:
                return null;
            }
    }
    
}
