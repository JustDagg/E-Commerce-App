package com.dagg.specification.custom;

import com.dagg.entity.Product;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Dagg
 * @created 27/10/2023
 */

@RequiredArgsConstructor
public class CustomProductSpecification implements Specification<Product> {

    @NonNull
    private String field;

    @NonNull
    private Object value;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (field.equalsIgnoreCase("name")) {
            return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
        }

        if (field.equalsIgnoreCase("minPrice")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value.toString());
        }

        if (field.equalsIgnoreCase("maxPrice")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), value.toString());
        }

        if (field.equalsIgnoreCase("minWeight")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("weight"), value.toString());
        }

        if (field.equalsIgnoreCase("maxWeight")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("weight"), value.toString());
        }

        if (field.equalsIgnoreCase("categoryName")) {
            return criteriaBuilder.like(root.get("category").get("name"), "%" + value.toString() + "%");
        }

        return null;
    }
}
