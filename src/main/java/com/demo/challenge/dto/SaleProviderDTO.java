package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleProviderDTO {

    private Integer id;
    private LocalDate date;
    private Integer customerId;
    private Integer quantity;
    private Double totalPrice;
    private List<ProductSaleDTO> products =  new ArrayList<>();
}
