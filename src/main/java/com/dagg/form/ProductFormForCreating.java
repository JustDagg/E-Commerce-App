package com.dagg.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dagg
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFormForCreating {

    private int id;

    private String name;

    private int availableQuantity;

    private double price;

    private double weight;

    private String description;

    private String imageName;

    private int categoryId;
}
