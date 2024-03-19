package org.example.miniprojectsqli.builder;

import org.example.miniprojectsqli.dto.ProductDto;
import org.example.miniprojectsqli.model.Product;

public class BuilderCreatorImpl implements BuildCreator{
    @Override
    public Product saveProduct(ProductDto newProduct) {
        return
                Product.builder()
                        .code(newProduct.getCode())
                        .price(newProduct.getPrice())
                        .libelle(newProduct.getLibelle())
                        .category(newProduct.getCategory())
                        .build();
    }

    @Override
    public ProductDto mapToProductDto(Product product) {
        return
                ProductDto.builder()
                .code(product.getCode())
                .libelle(product.getLibelle())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }
}
