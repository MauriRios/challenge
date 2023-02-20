package com.demo.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LowProductProviderDTO {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Boolean status;
    private ProductProviderDTO.ProviderInfoDTO provider;
}
