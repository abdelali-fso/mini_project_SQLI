package org.example.miniprojectsqli.controller;


import org.example.miniprojectsqli.dto.*;
import org.example.miniprojectsqli.handler.ResponseHandler;
import org.example.miniprojectsqli.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<ResponseListDto> getProducts() {
        return ResponseHandler.handleListResponse(productService.getAllProducts());
    }
    @PostMapping
    public ResponseEntity<ResponseDto> addProduct(@RequestBody ProductDto newProduct){
        return ResponseHandler.handleResponse(productService.addProduct(newProduct));
    }
    @PutMapping("/{code}")
    public ResponseEntity<ResponseDto> updateProduct(@RequestBody ProductDto updatedProduct,@PathVariable Long code){
        return ResponseHandler.handleResponse(productService.updateProduct(updatedProduct,code));
    }
    @DeleteMapping("/{code}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable Long code) {
        boolean isDeleted = productService.deleteProduct(code);
        return ResponseHandler.handleDeleteResponse(isDeleted);
    }

}
