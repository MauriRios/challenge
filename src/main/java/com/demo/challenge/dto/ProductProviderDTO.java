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
public class ProductProviderDTO {

    private Integer id;
    private ProviderDTO provider;
    private String providerName;
    private String description;
    private BigDecimal price;
    private int stock;
    private Boolean status;
}
