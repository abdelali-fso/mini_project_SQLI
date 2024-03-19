package org.example.miniprojectsqli.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniprojectsqli.model.Category;

/**
 * dto productDto for Product
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long code;
    private String libelle;
    private Double price;
    private Category category;

}
