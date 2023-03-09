package com.kirilyuk.test_oracle.dao;

import com.kirilyuk.test_oracle.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDAO extends JpaRepository<Orders, Long> {
}