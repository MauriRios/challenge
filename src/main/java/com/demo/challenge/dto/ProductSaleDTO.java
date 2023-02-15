package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleDTO {

    private Integer id;
    private String providerName;
    private String description;
    private Double price;
    private Integer quantity;

}
