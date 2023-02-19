package com.demo.challenge.dtos;

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
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer quantity;

}
