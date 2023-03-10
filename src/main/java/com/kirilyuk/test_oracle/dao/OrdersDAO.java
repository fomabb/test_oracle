package com.kirilyuk.test_oracle.dao;

import com.kirilyuk.test_oracle.dto.OrdersReportDTO;
import com.kirilyuk.test_oracle.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDAO extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o where o.goods and o.docDate ilike :text")
    List<Orders> getDate(@Param("text") String text);
}
