package org.example.miniprojectsqli.builder;

import org.example.miniprojectsqli.dto.ProductDto;
import org.example.miniprojectsqli.model.Product;

public interface BuildCreator {
    Product saveProduct(ProductDto newProduct);
    ProductDto mapToProductDto(Product product);
}
