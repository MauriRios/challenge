/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.dtos.*;
import com.demo.challenge.entities.OrderDetail;
import com.demo.challenge.entities.Product;
import com.demo.challenge.entities.Sale;
import com.demo.challenge.exceptions.RequestException;
import com.demo.challenge.repositories.*;
import com.demo.challenge.servicesInterfaces.ISaleService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author mauri
 */

@Service
public class ImpSaleService implements ISaleService {


    private final ISaleRepository isaleRepository;
    private final IProviderRepository iproviderRepository;
    private final ICustomerRepository icustomerRepository;
    private final IProductRepository iproductRepository;
    private final IOrderDetailRepository iorderDetailRepository;

    public ImpSaleService(ISaleRepository isaleRepository, IProviderRepository iproviderRepository, ICustomerRepository icustomerRepository, IProductRepository iproductRepository, IOrderDetailRepository iorderDetailRepository) {
        this.isaleRepository = isaleRepository;
        this.iproviderRepository = iproviderRepository;
        this.icustomerRepository = icustomerRepository;
        this.iproductRepository = iproductRepository;
        this.iorderDetailRepository = iorderDetailRepository;
    }

    ModelMapper mapper = new ModelMapper();

    JSONObject response = new JSONObject();


    @Override
    public List<SaleDTO> getSales() {
        var sales = isaleRepository.findAll();
        if (sales.isEmpty()){
            throw new RequestException("P-803","No hay ventas realizadas");
        }
        List<SaleDTO> saleDTO = new ArrayList<>();

        for (var unit : sales) {
            var purchases = mapper.map(unit, SaleDTO.class);
            purchases.setCustomerId(unit.getCustomer().getId());
            purchases.setProviderId(unit.getProvider_id().getId());
            purchases.setTotalPrice(unit.getTotalPrice());
            saleDTO.add(purchases);
        }

        return saleDTO;
    }

    @Transactional
    @Override
    public ResponseEntity<String> createSale(SaleDTO saleDTO) {
        try {
            var customer = icustomerRepository.findById(saleDTO.getCustomerId()).get();
            var provider = iproviderRepository.findById(saleDTO.getProviderId()).get();
            LocalDate today = LocalDate.now();
            List<Product> products = new ArrayList<>();
            saleDTO.setDate(today);
            var sale = mapper.map(saleDTO, Sale.class);
            sale.setCustomer(customer);
            sale.setProvider_id(provider);
            int totalQuantity = 0;
            BigDecimal totalPrice = new BigDecimal("0.0");
            for (var unit : saleDTO.getProducts()) {
                var product = iproductRepository.findById(unit.getId()).get();
                OrderDetail orderDetail = new OrderDetail();
                if (product.getStock() >= unit.getQuantity()) {
                    var result = product.getStock() - unit.getQuantity();
                    product.setPrice(unit.getPrice());
                    product.setStock(result);
                    totalQuantity += unit.getQuantity();
                    totalPrice = totalPrice.add(unit.getPrice().multiply(new BigDecimal(unit.getQuantity())));
                    // crea y persiste un objeto OrderDetail para este producto
                    orderDetail.setSale(sale);
                    orderDetail.setProduct(product);
                    orderDetail.setQuantity(unit.getQuantity());
                    iorderDetailRepository.save(orderDetail);
                    products.add(mapper.map(unit, Product.class));
                } else {
                    response.put("message", "Producto sin Stock, vuelva mas tarde");
                    return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
                }

                iproductRepository.save(product);
            }
            saleDTO.setTotalPrice(totalPrice);
            sale.setTotalPrice(totalPrice);
            sale.setQuantity(totalQuantity);
            sale.setProducts(products);
            isaleRepository.save(sale);
            response.put("message", "Compra realizada con Exíto");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);

        } catch (RuntimeException ex) {
            throw new RequestException("P-804","Compra no realizada, Algo salió mal, datos de la compra o producto faltante");
        }
    }

    @Override
    public SaleDTO findSaleById(int id) {
        try {
            Optional<Sale> sale = isaleRepository.findById(id);
            if (sale.get().getId() != 0) {
                SaleDTO saleDTO = (mapper.map(sale, SaleDTO.class));
                return saleDTO;
            }
        }catch (NoSuchElementException ex) {
            throw new RequestException("P-801", "Id de la compra no encontrado");
        }

        return null;
    }

    //querys

    @Override
    public List<SaleDTO> findSaleByDate(LocalDate date) {
        List<Sale> sales = isaleRepository.findSaleByDate(date);

        if (sales.isEmpty()){
            throw new RequestException("P-805","No hay ventas realizadas el dia " + date);
        }
        List<SaleDTO> saleDTO = new ArrayList<>();

        for (var unit : sales) {
            saleDTO.add(mapper.map(unit, SaleDTO.class));
        }
            return saleDTO;
    }


    @Override
    public List<SaleDTO> getSalesByProviderId(Integer providerId) {
        List<Sale> sales = isaleRepository.getSalesByProviderId(providerId);
        if (sales.isEmpty()){
            throw new RequestException("P-806","El proveedor no realizo ventas");
        }
        List<SaleDTO> saleDTO = new ArrayList<>();

        for (var unit : sales) {
            saleDTO.add(mapper.map(unit, SaleDTO.class));
        }
        return saleDTO;
    }


}
