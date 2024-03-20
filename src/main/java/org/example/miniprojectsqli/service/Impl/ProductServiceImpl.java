package org.example.miniprojectsqli.service.Impl;


import org.example.miniprojectsqli.builder.BuildCreator;
import org.example.miniprojectsqli.builder.BuilderCreatorImpl;
import org.example.miniprojectsqli.dto.ProductDto;
import org.example.miniprojectsqli.filtre.Filter;
import org.example.miniprojectsqli.filtre.checkFiltre;
import org.example.miniprojectsqli.model.Product;
import org.example.miniprojectsqli.repository.ProductRepository;
import org.example.miniprojectsqli.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    //instance the logger for pass the message
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    //Instance for filtre the price
    private Filter filter = new checkFiltre();
    //Instance for builder
    private BuildCreator buildCreator = new BuilderCreatorImpl();
    //Inject the repository
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     *
     * @param newProduct ProductDto to save
     * @return ProductDto
     * First check if the product exist by Optional.isPresent()
     * then call the function isPositiveAndSupAZero() to check the price
     * save the product or show the error
     */
    @Override
    public Optional<ProductDto> addProduct(ProductDto newProduct) {
        try {
            filter.isPositiveAndSupAZero(newProduct.getPrice());

            Optional<Product> optionalProduct = productRepository.findByCode(newProduct.getCode());

            filter.isPresent(optionalProduct);

            Product savedProduct = productRepository.save(buildCreator.saveProduct(newProduct));
            logger.info("Product saved successfully");
            return Optional.of(buildCreator.mapToProductDto(savedProduct));
        } catch (Exception e) {
            logger.error("Error in adding product: " + e.getMessage());
            throw new RuntimeException("Failed to add product", e); // Throw an exception
        }
    }


    /**
     *
     * @param code take the unique code for product
     * First check if the product exist by Optional.isPresent()
     * delete the product or show the error message
     */
    @Override
    public boolean deleteProduct(Long code) {
        try {
            Optional<Product> optionalProduct = productRepository.findByCode(code);
            if (optionalProduct.isPresent()) {
                productRepository.deleteById(optionalProduct.get().getProductId());
                logger.info("Product with code " + code + " deleted successfully");
                return true;
            } else {
                logger.error("The product with code " + code + " does not exist");
                throw new IllegalArgumentException("Product with code " + code + " does not exist");
            }
        } catch (Exception e) {
            logger.error("Error in deleting product: " + e.getMessage());
            throw new RuntimeException("Failed to delete product", e); // Throw an exception
        }
    }

    /**
     *
     * @param productDto ProductDto to update
     * @return ProductDto
     * First check if the product exist by Optional.isPresent()
     * then call the function isPositiveAndSupAZero() to check the price
     * update the product or show the error
     */
    @Override
    public Optional<ProductDto> updateProduct(ProductDto productDto, Long code) {
        try {
            filter.isPositiveAndSupAZero(productDto.getPrice());

            Optional<Product> optionalProduct = productRepository.findByCode(code);

            filter.isPresent(optionalProduct);

            Product updatedProduct = buildCreator.saveProduct(productDto);

            Product savedProduct = productRepository.save(updatedProduct);

            return Optional.of(buildCreator.mapToProductDto(savedProduct));
        } catch (Exception e) {
            logger.error("Error in updating product: " + e.getMessage());
            throw new RuntimeException("Failed to update product", e); // Throw an exception
        }
    }

    /**
     *
     * @return List of product dto
     * fetch all products exist
     */
    @Override
    public Optional<List<ProductDto>> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            List<ProductDto> productDtos = products.stream()
                    .map(product -> buildCreator.mapToProductDto(product))
                    .collect(Collectors.toList());

            return Optional.of(productDtos);
        } catch (Exception e) {
            logger.error("Error in getting all products: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve all products", e); // Throw an exception
        }
    }


}
