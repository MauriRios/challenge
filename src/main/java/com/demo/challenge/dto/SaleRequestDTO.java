package com.demo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequestDTO {

    private int id;
    private LocalDate date;
    private double total;
    private Long providerId;
    private Long customerId;
    private Set<ProductSaleDTO> productos =  new HashSet<>();

}
