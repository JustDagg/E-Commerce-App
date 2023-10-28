package com.dagg.specification.custom;

import com.dagg.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * @author Dagg
 * @created 28/10/2023
 */

@RequiredArgsConstructor
public class CustomUserSpecification implements Specification<User> {

    @NonNull
    private String field;

    @NonNull
    private Object value;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (field.equalsIgnoreCase("name")) {
            return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
        }

        if (field.equalsIgnoreCase("username")) {
            return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
        }

        if (field.equalsIgnoreCase("email")) {
            return criteriaBuilder.like(root.get("email"), "%" + value.toString() + "%");
        }

        return null;
    }
}
