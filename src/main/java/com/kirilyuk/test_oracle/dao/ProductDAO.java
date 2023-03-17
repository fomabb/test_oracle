package com.kirilyuk.test_oracle.dao;

import com.kirilyuk.test_oracle.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Goods, Long> {

    @Query("select g from Goods g where g.order=null ")
    List<Goods> getAllGoods();

    @Query("select g from Goods g where g.order!=null ")
    List<Goods> getAllGootsOrder();

    @Query(value = "select * from goods g where g.order_id=?1", nativeQuery = true)
    List<Goods> getAllOrdersById(@Param("id") Long id);

    @Query(value = "select sum (price) from goods g where g.order_id=:id", nativeQuery = true)
    double sumPrice(@Param("id") Long id);


    @Query(value = "select sum (g.quantity) from goods g where g.order_id=:id", nativeQuery = true)
    double sumQuantity(@Param("id") Long id);

    @Query(value = "select count(*) from goods g where g.order_id=?1", nativeQuery = true)
    int countGoods(@Param("id") Long id);

    @Query(value = "select o.doc_date from orders o where o.id=?1", nativeQuery = true)
    String date(@Param("id") Long id);

    @Query("select g from Goods g where g.goodCode ilike %:text% and g.order=null")
    List<Goods> search(@Param("text") String text);
}
