package org.example.miniprojectsqli.service;


import org.example.miniprojectsqli.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductDto> addProduct(ProductDto newProduct);
    boolean deleteProduct(Long code);
    Optional<ProductDto> updateProduct(ProductDto product,Long code);
    Optional<List<ProductDto>> getAllProducts();
}
