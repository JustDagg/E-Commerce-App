package com.dagg.form;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dagg
 * @created 27/10/2023
 */

@Data
@NoArgsConstructor
public class ProductFilterForm {

    private Double minPrice;

    private Double maxPrice;

    private Double minWeight;

    private Double maxWeight;

}
