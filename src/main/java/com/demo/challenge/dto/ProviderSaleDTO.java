package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderSaleDTO {

    private Integer id;
    private String providerName;
    private String providerLastName;
    private String phone;
    private String address;
    private Integer cuit;
    private List<ProductDTO> products  = new ArrayList<>();
    private List<SaleProviderDTO> sales  = new ArrayList<>();
    private Boolean status;
}
