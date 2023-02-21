/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.challenge.services;

import com.demo.challenge.dtos.*;
import com.demo.challenge.entities.Product;
import com.demo.challenge.entities.Sale;
import com.demo.challenge.exceptions.RequestException;
import com.demo.challenge.repositories.ICustomerRepository;
import com.demo.challenge.repositories.IProductRepository;
import com.demo.challenge.repositories.IProviderRepository;
import com.demo.challenge.repositories.ISaleRepository;
import com.demo.challenge.servicesInterfaces.ISaleService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import org.modelmapper.ModelMapper;
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

    public ImpSaleService(ISaleRepository isaleRepository, IProviderRepository iproviderRepository, ICustomerRepository icustomerRepository, IProductRepository iproductRepository) {
        this.isaleRepository = isaleRepository;
        this.iproviderRepository = iproviderRepository;
        this.icustomerRepository = icustomerRepository;
        this.iproductRepository = iproductRepository;
    }

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<SaleDTO> getSales() {
        var sales = isaleRepository.findAll();
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
    public String createSale(SaleDTO saleDTO) {

        try {

            var customer = icustomerRepository.findById(saleDTO.getCustomerId()).get();
            var provider = iproviderRepository.findById(saleDTO.getProviderId()).get();
            LocalDate today = LocalDate.now();
            List<Product> products = new ArrayList<>();

            int totalQuantity = 0;
            BigDecimal totalPrice = new BigDecimal("0.0");

            for (var unit : saleDTO.getProducts()) {
                var product = iproductRepository.findById(unit.getId()).get();

                if (product.getStock() >= unit.getQuantity()) {
                    var result = product.getStock() - unit.getQuantity();

                    product.setStock(result);
                    totalQuantity += unit.getQuantity();
                    totalPrice = totalPrice.add(unit.getPrice().multiply(new BigDecimal(unit.getQuantity())));

                    product.setQuantity(unit.getQuantity());
                    products.add(mapper.map(unit, Product.class));

                } else {
                    return "Producto sin Stock, vuelva mas tarde";
                }
                iproductRepository.save(product);
            }

            saleDTO.setDate(today);
            saleDTO.setTotalPrice(totalPrice);
            var sale = mapper.map(saleDTO, Sale.class);
            sale.setCustomer(customer);
            sale.setProvider_id(provider);
            sale.setProducts(products);
            sale.setTotalPrice(totalPrice);
            sale.setQuantity(totalQuantity);
            isaleRepository.save(sale);
            return "Compra realizada con Exíto";

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
        List<SaleDTO> saleDTO = new ArrayList<>();
        for (var unit : sales) {
            saleDTO.add(mapper.map(unit, SaleDTO.class));
        }
        if (saleDTO.size() > 0) {
            return saleDTO;
        } else {
            return null;
        }
    }


    @Override
    public List<SaleDTO> getSalesByProviderId(Integer providerId) {
        List<Sale> sales = isaleRepository.getSalesByProviderId(providerId);
        List<SaleDTO> saleDTO = new ArrayList<>();
        for (var unit : sales) {
            saleDTO.add(mapper.map(unit, SaleDTO.class));
        }
        return saleDTO;
    }


}
