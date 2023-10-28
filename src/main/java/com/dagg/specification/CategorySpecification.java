package com.dagg.specification;

import com.dagg.entity.Category;
import com.dagg.specification.custom.CustomCategorySpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

/**
 * @author Dagg
 * @created 27/10/2023
 */
public class CategorySpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Category> buildWhere(String search) {

        Specification<Category> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomCategorySpecification name = new CustomCategorySpecification("name", search);

            where = Specification.where(name);
        }

        System.out.println(where);
        return where;
    }

}
