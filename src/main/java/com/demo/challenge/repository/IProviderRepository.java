/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.repository;

import com.demo.challenge.dto.SaleDTO;
import com.demo.challenge.entitys.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

/**
 *
 * @author mauri
 */

@Repository
public interface IProviderRepository extends JpaRepository<Provider, Integer> {

    @Query("select new com.demo.challenge.dto.SaleDTO(s.id, s.date, s.quantity, s.total) from Sale s where s.provider.id = :providerId")
    List<SaleDTO> findByProviderId(@Param("providerId") int providerId);

}
