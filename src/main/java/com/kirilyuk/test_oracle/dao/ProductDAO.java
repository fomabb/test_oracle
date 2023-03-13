package com.kirilyuk.test_oracle.dao;

import com.kirilyuk.test_oracle.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Goods, Long> {

    @Query("select g from Goods g where g.order=null ")
    List<Goods> getAllGoods();
}
