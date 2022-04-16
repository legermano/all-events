package com.legermano.allevents.helper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecificationSearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private boolean orPredicate;

    public SpecificationSearchCriteria() {}

    public SpecificationSearchCriteria(String key, SearchOperation operation, Object value) {
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SpecificationSearchCriteria(String orPredicate, String key, SearchOperation operation, Object value) {
        super();
        this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SpecificationSearchCriteria(String key, String operation, String prefix, String value, String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));

        if(op != null) {
            // The operation may be complex operation
            if(op == SearchOperation.EQUALITY) {
                final Boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final Boolean endWithAsterisk   = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if(startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if(startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if(endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
        }

        this.key = key;
        this.operation = op;
        this.value = value;
    }
}
