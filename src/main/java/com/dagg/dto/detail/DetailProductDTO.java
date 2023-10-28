package com.dagg.dto.detail;

import com.dagg.dto.CategoryDTO;
import com.dagg.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dagg
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailProductDTO {

    private int id;

    private String name;

    private double price;

    private double weight;

    private String description;

    private String imageName;

    private CategoryDTO category;

}
