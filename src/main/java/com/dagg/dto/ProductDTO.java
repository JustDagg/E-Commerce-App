package com.dagg.dto;

import com.dagg.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dagg
 */

@Data
@NoArgsConstructor
public class ProductDTO {

    private int id;

    private String name;

    private int availableQuantity;

    private double price;

    private double weight;

    private String description;

    private String imageName;

    private int categoryId;

    public ProductDTO(int id, String name, String imageName) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
    }
}
