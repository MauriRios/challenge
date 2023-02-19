package com.demo.challenge.dtos;

import lombok.*;

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
    private Integer stock;
    private Boolean status;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProviderInfoDTO{
        private Long id;
        private String providerName;
        private String providerLastName;
        private String phone;
        private String address;
        private int cuit;
        private Boolean status;
    }
}
