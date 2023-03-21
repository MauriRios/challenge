package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.dtos.OrderDetailDTO;
import com.demo.challenge.dtos.SaleDTO;
import com.demo.challenge.entities.OrderDetail;

import java.util.List;

public interface IOrderDetailService {

    public OrderDetailDTO getSalesBySaleId(Integer saleId);

    public List<OrderDetailDTO> getOrderDetails();
}
