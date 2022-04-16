package com.legermano.allevents.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecificationsBuilder<T> {
    private final List<SpecificationSearchCriteria> params;

    public GenericSpecificationsBuilder() {
        this.params = new ArrayList<>();
    }

    public final GenericSpecificationsBuilder<T> with(String key, String operation, Object value, String prefix, String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final GenericSpecificationsBuilder<T> with(String precedenceIndicator, String key, String operation, Object value, String prefix, String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));

        if(op != null) {
            // The operation may be complex operation
            if(op == SearchOperation.EQUALITY) {
                final Boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final Boolean endWithAsterisk   = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }

            params.add(new SpecificationSearchCriteria(precedenceIndicator, key, op, value));
        }

        return this;
    }

    public Specification<T> build(Function<SpecificationSearchCriteria, Specification<T>> converter) {
        if(params.size() == 0) {
            return null;
        }

        final List<Specification<T>> specs = params.stream()
            .map(converter)
            .collect(Collectors.toCollection(ArrayList::new));

        Specification<T> result = specs.get(0);

        for(int i = 1; i < specs.size(); i++) {
            result = params.get(i)
                .isOrPredicate()
                    ? Specification.where(result)
                        .or(specs.get(i))
                    : Specification.where(result)
                        .and(specs.get(i));
        }

        return result;
    }

    public Specification<T> build(Deque<?> postFixedExprStack, Function<SpecificationSearchCriteria, Specification<T>> converter) {
        Deque<Specification<T>> specStack = new LinkedList<>();

        Collections.reverse((List<?>) postFixedExprStack);

        while(!postFixedExprStack.isEmpty()) {
            Object mayBeOperand = postFixedExprStack.pop();

            if(!(mayBeOperand instanceof String)) {
                specStack.push(converter.apply((SpecificationSearchCriteria) mayBeOperand));
            } else {
                Specification<T> openrand1 = specStack.pop();
                Specification<T> openrand2 = specStack.pop();

                if(mayBeOperand.equals(SearchOperation.AND_OPERATOR)) {
                    specStack.push(Specification.where(openrand1).and(openrand2));
                } else if(mayBeOperand.equals(SearchOperation.OR_OPERATOR)) {
                    specStack.push(Specification.where(openrand1).or(openrand2));
                }
            }
        }

        return specStack.pop();
    }
}
