package com.demo.challenge.repositories;

import com.demo.challenge.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query(value = "SELECT * FROM challenge.order_detail where sale_id =:saleId", nativeQuery = true )
    List<OrderDetail> getSalesBySaleId(@Param("saleId") Integer saleId);

}
