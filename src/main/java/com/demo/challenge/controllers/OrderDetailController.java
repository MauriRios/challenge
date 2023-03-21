package com.demo.challenge.controllers;

import com.demo.challenge.dtos.OrderDetailDTO;
import com.demo.challenge.entities.OrderDetail;
import com.demo.challenge.repositories.IOrderDetailRepository;
import com.demo.challenge.servicesInterfaces.IOrderDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("venta")
@CrossOrigin(origins = "*")
public class OrderDetailController {
    private final IOrderDetailService iorderDetailService;
    private final IOrderDetailRepository iorderDetailRepository;

    public OrderDetailController(IOrderDetailService iorderDetailService, IOrderDetailRepository iorderDetailRepository) {
        this.iorderDetailService = iorderDetailService;
        this.iorderDetailRepository = iorderDetailRepository;
    }


    @GetMapping("/orden{id}")
    public OrderDetailDTO getSalesBySaleId(@RequestParam("saleId") Integer saleId) {

        return iorderDetailService.getSalesBySaleId(saleId);

    }
    @GetMapping("/ordenes")
    public List<OrderDetailDTO> getOrderDetails() {
        return iorderDetailService.getOrderDetails();

    }
}
