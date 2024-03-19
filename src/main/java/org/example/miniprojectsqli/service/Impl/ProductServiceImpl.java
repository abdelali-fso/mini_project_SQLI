package org.example.miniprojectsqli.service.Impl;


import org.example.miniprojectsqli.builder.BuildCreator;
import org.example.miniprojectsqli.builder.BuilderCreatorImpl;
import org.example.miniprojectsqli.dto.ProductDto;
import org.example.miniprojectsqli.filtre.FilterPrice;
import org.example.miniprojectsqli.filtre.checkPositive;
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
    private FilterPrice filterPrice = new checkPositive();
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
        if (!filterPrice.isPositiveAndSupAZero(newProduct.getPrice())) {
            logger.error("The price must be greater than strictly zero. Price: " + newProduct.getPrice());
            return Optional.empty();
        }

        Long newProductCode = newProduct.getCode();
        Optional<Product> optionalProduct = productRepository.findByCode(newProductCode);
        if (optionalProduct.isPresent()) {
            logger.error("This product code already exists");
            return Optional.empty();
        }

        Product savedProduct = productRepository.save(
                buildCreator.saveProduct(newProduct)
        );
        logger.info("Product saved successfully");
        return Optional.of(buildCreator.mapToProductDto(savedProduct));
    }


    /**
     *
     * @param code take the unique code for product
     * First check if the product exist by Optional.isPresent()
     * delete the product or show the error message
     */
    @Override
    public boolean deleteProduct(Long code) {
        Optional<Product> optionalProduct = productRepository.findByCode(code);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(optionalProduct.get().getProductId());
            logger.info("Product with code " + code + " deleted successfully");
            return true;
        } else {
            logger.error("The product with code " + code + " does not exist");
            return false;
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
    public Optional<ProductDto> updateProduct(ProductDto productDto,Long code) {
        if (!filterPrice.isPositiveAndSupAZero(productDto.getPrice())) {
            logger.error("The price must be greater than strictly zero. Price: " + productDto.getPrice());
            return Optional.empty();
        }

        Optional<Product> optionalProduct = productRepository.findByCode(code);

        if (!optionalProduct.isPresent()) {
            logger.error("This product does not exist");
            return Optional.empty();
        }

        Product updatedProduct = buildCreator.saveProduct(productDto);

        Product savedProduct = productRepository.save(updatedProduct);

        return Optional.of(buildCreator.mapToProductDto(savedProduct));
    }

    /**
     *
     * @return List of product dto
     * fetch all products exist
     */
    @Override
    public Optional<List<ProductDto>> getAllProducts() {
        try{
            List<Product> products = productRepository.findAll();
            List<ProductDto> productDtos = products.stream()
                    .map(product -> buildCreator.mapToProductDto(product))
                    .collect(Collectors.toList());

            return Optional.of(productDtos);
        }catch (Exception e){
            logger.error("Error in getting all products: " + e.getMessage());
            return Optional.empty();
        }
    }


}
