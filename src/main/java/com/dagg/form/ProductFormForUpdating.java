package com.dagg.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dagg
 * @created 26/10/2023
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFormForUpdating {

    private int id;

    private String name;

    private int availableQuantity;

    private double price;

    private double weight;

    private String description;

    private String imageName;

    private int categoryId;

}
