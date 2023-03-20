package com.demo.challenge.dtos;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderProductDTO {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
}
