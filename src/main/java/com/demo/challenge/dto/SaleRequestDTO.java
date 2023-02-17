package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequestDTO {

    private Integer id;
    private LocalDate date;
    private Integer providerId;
    private Integer customerId;
    private BigDecimal totalPrice;
    private Integer quantity;
    private Set<ProductSaleDTO> products =  new HashSet<>();

}
