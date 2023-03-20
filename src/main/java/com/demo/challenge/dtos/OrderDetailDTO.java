package com.demo.challenge.dtos;

import com.demo.challenge.entities.Product;
import com.demo.challenge.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Integer id;
    private Sale sale;
    private List<OrderProductDTO> product;
    private int quantity;
}
