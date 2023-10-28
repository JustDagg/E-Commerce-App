package com.dagg.specification;


import com.dagg.entity.Product;
import com.dagg.form.ProductFilterForm;
import com.dagg.specification.custom.CustomProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

/**
 * @author Dagg
 * @created 27/10/2023
 */
public class ProductSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Product> buildWhere(String search, ProductFilterForm productFilterForm) {

        Specification<Product> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomProductSpecification name = new CustomProductSpecification("name", search);
            CustomProductSpecification categoryName = new CustomProductSpecification("categoryName", search);

            where = Specification.where(name).or(categoryName);
        }

        if (productFilterForm != null && productFilterForm.getMinPrice() != null) {
            CustomProductSpecification minPrice = new CustomProductSpecification("minPrice",
                    productFilterForm.getMinPrice());
            if (where == null) {
                where = minPrice;
            } else {
                where = where.and(minPrice);
            }
        }

        if (productFilterForm != null && productFilterForm.getMaxPrice() != null) {
            CustomProductSpecification maxPrice = new CustomProductSpecification("maxPrice",
                    productFilterForm.getMaxPrice());
            if (where == null) {
                where = maxPrice;
            } else {
                where = where.and(maxPrice);
            }
        }

        if (productFilterForm != null && productFilterForm.getMinWeight() != null) {
            CustomProductSpecification minWeight = new CustomProductSpecification("minWeight",
                    productFilterForm.getMinWeight());
            if (where == null) {
                where = minWeight;
            } else {
                where = where.and(minWeight);
            }
        }

        if (productFilterForm != null && productFilterForm.getMaxWeight() != null) {
            CustomProductSpecification maxWeight = new CustomProductSpecification("maxWeight",
                    productFilterForm.getMaxWeight());
            if (where == null) {
                where = maxWeight;
            } else {
                where = where.and(maxWeight);
            }
        }

        System.out.println(where);
        return where;
    }

}
