package org.example.miniprojectsqli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto to handle the response of each product with the status of operation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private ProductDto productDto;
    private String status;
}
