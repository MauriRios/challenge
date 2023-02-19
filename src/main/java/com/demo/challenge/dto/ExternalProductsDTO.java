package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalProductsDTO {

    private String name;
    private String sku;
    private String stock;
    private  String imgUr;
}
