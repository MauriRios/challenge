/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.repositories;

import com.demo.challenge.entities.Sale;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author mauri
 */

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Integer> {

    @Query("SELECT s FROM Sale s WHERE s.date = :date")
    List<Sale> findSaleByDate(@Param("date") LocalDate date);

    @Query(value = "select s from Sale s where s.id = :providerId")
    List<Sale> getSalesByProviderId(@Param("providerId") Integer id);


}
