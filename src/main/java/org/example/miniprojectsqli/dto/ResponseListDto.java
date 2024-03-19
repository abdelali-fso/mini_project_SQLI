package org.example.miniprojectsqli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * dto to handle the response of list of product with the status of operation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseListDto {
    private List<ProductDto> productDtoS;
    private String status;
}
