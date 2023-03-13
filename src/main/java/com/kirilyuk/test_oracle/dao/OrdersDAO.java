package com.kirilyuk.test_oracle.dao;

import com.kirilyuk.test_oracle.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface OrdersDAO extends JpaRepository<Orders, Long> {

    @Query("select sum(g.price) from Goods g where g.order=:id")
    List<Orders> countOrder(@PathVariable("id") Long id);
}
