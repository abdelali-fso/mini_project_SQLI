package org.example.miniprojectsqli.handler;


import org.example.miniprojectsqli.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class ResponseHandler {
    /**
     *
     * @param result we pass the result of our service function
     * @return the responseEntity with the correct status
     * this function we use it in the fetching all products
     */
    public static ResponseEntity<ResponseListDto> handleListResponse(Optional<List<ProductDto>> result) {
        if (result.isPresent()) {
            List<ProductDto> productDtos = result.get();
            ResponseListDto responseListDto = new ResponseListDto(productDtos, "Success");
            return ResponseEntity.ok(responseListDto);
        } else {
            ResponseListDto responseListDto = new ResponseListDto(null, "No Content");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseListDto);
        }
    }


    /**
     *
     * @param result we pass the result of our service function
     * @return the responseEntity with the correct status
     * this function we use it in add new product and update product
     */
    public static ResponseEntity<ResponseDto> handleResponse(Optional<ProductDto> result) {
        return result.map(productDto -> ResponseEntity.ok(new ResponseDto(productDto, "Success")))
                .orElseGet(() -> ResponseEntity.ok(new ResponseDto(null, "Error")));
    }



    /**
     *
     * @param success pass the result of our service function
     * @return the responseEntity with the correct status
     * this function we use it in delete a product by code
     */
    public static ResponseEntity<ResponseDto> handleDeleteResponse(boolean success) {
        if (success) {
            ResponseDto responseDto = new ResponseDto(null, "Success");
            return ResponseEntity.noContent().build();
        } else {
            ResponseDto responseDto = new ResponseDto(null, "Error");
            return ResponseEntity.notFound().build();
        }
    }

}
