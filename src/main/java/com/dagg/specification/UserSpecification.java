package com.dagg.specification;

import com.dagg.entity.User;
import com.dagg.specification.custom.CustomProductSpecification;
import com.dagg.specification.custom.CustomUserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

/**
 * @author Dagg
 * @created 28/10/2023
 */
public class UserSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<User> buildWhere(String search) {

        Specification<User> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomUserSpecification name = new CustomUserSpecification("name", search);
            CustomUserSpecification username = new CustomUserSpecification("username", search);
            CustomUserSpecification email = new CustomUserSpecification("email", search);

            where = Specification.where(name).or(username).or(email);
        }
        System.out.println(where);
        return where;
    }
}
