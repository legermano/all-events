package com.legermano.allevents.util.genericSearch;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteriaList {
    private List<SearchCriteria> filter;

    public Deque<?> parse() {
        Deque<Object> output = new LinkedList<>();

        for (SearchCriteria criteria : filter) {
            output.push(new SpecificationSearchCriteria(
                criteria.getKey(),
                SearchOperation.getSimpleOperation(criteria.getOperation().toCharArray()[0]),
                criteria.getValue()
            ));
        }

        return output;
    }
}
