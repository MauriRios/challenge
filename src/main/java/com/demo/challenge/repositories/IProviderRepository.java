/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.repositories;

import com.demo.challenge.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauri
 */

@Repository
public interface IProviderRepository extends JpaRepository<Provider, Integer> {

//    @Query("select new com.demo.challenge.dto.SaleRequestDTO(s.id, s.date, s.quantity, s.total) from Sale s where s.provider.id = :providerId")
//    List<SaleRequestDTO> findByProviderId(@Param("providerId") int providerId);

}
