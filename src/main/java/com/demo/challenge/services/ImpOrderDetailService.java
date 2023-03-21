package com.demo.challenge.services;

import com.demo.challenge.dtos.OrderDetailDTO;
import com.demo.challenge.dtos.OrderProductDTO;
import com.demo.challenge.dtos.SaleDTO;
import com.demo.challenge.dtos.SaleOrderDTO;
import com.demo.challenge.entities.OrderDetail;
import com.demo.challenge.entities.Product;
import com.demo.challenge.entities.Sale;
import com.demo.challenge.exceptions.RequestException;
import com.demo.challenge.repositories.IOrderDetailRepository;
import com.demo.challenge.servicesInterfaces.IOrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImpOrderDetailService implements IOrderDetailService {
    private final IOrderDetailRepository iorderDetailRepository;

    public ImpOrderDetailService(IOrderDetailRepository iorderDetailRepository) {
        this.iorderDetailRepository = iorderDetailRepository;
    }
    ModelMapper mapper = new ModelMapper();

    @Override
    public List<OrderDetailDTO> getOrderDetails() {
        List<OrderDetail> orderDetails = iorderDetailRepository.findAll();

        if (orderDetails.isEmpty()){
            throw new RequestException("P-906","No se encontraron detalles de ventas con el ID seleccionado");
        }

        List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();

        for (var sale : orderDetails.stream().map(OrderDetail::getSale).distinct().collect(Collectors.toList())) {
            SaleOrderDTO saleDTO = mapper.map(sale, SaleOrderDTO.class);
            List<OrderProductDTO> orderProductDTOs = new ArrayList<>();

            for (var unit : orderDetails.stream().filter(detail -> detail.getSale().equals(sale)).collect(Collectors.toList())) {
                Product product = unit.getProduct();
                var unitQuantity = unit.getQuantity();
                OrderProductDTO orderProductDTO = mapper.map(product, OrderProductDTO.class);
                orderProductDTO.setQuantity(unitQuantity);
                orderProductDTOs.add(orderProductDTO);
            }

            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setSale(saleDTO);
            orderDetailDTO.setProduct(orderProductDTOs);
            orderDetailDTOs.add(orderDetailDTO);
        }

        return orderDetailDTOs;
    }

    @Override
    public OrderDetailDTO getSalesBySaleId(Integer saleId) {
        List<OrderDetail> orderDetail = iorderDetailRepository.getSalesBySaleId(saleId);

        if (orderDetail.isEmpty()){
            throw new RequestException("P-906","No se encontraron detalles de ventas con el ID seleccionado");
        }
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        List<OrderProductDTO> orderProductDTO = new ArrayList<>();
        SaleOrderDTO saleDTO = new SaleOrderDTO();

            for (var saleUnit : orderDetail) {

                Sale sale = saleUnit.getSale();
                saleDTO = mapper.map(sale, SaleOrderDTO.class);
            }
            for (var unit : orderDetail) {

                Product product = unit.getProduct();
                var unitQuantity = unit.getQuantity();
                OrderProductDTO orderProduct = mapper.map(product, OrderProductDTO.class);
                orderProduct.setQuantity(unitQuantity);
                orderProductDTO.add(orderProduct);

            }


        orderDetailDTO.setSale(saleDTO);
        orderDetailDTO.setProduct(orderProductDTO);

        return orderDetailDTO;
    }
}
