/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.repository;

import com.demo.challenge.dto.SaleDTO;
import com.demo.challenge.entitys.Sale;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauri
 */

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Integer> {

    @Query("SELECT s FROM Sale s WHERE s.date = :date")
    List<Sale> findByDate(@Param("date") LocalDate date);

  //  @Query("SELECT sale FROM Sale sale WHERE sale.provider.id = :providerId")
   // List<Sale> findByProviderId(@Param("providerId") int providerId);

    @Query("SELECT new com.demo.challenge.dto.SaleDTO(s.id, s.date, s.quantity, s.total, s.products) FROM Sale s WHERE s.provider.id = :providerId")
    List<SaleDTO> findByProviderId(@Param("providerId") int providerId);
}
