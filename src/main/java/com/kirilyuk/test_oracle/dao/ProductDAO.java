package com.kirilyuk.test_oracle.dao;

import com.kirilyuk.test_oracle.dto.QuantityUpdate;
import com.kirilyuk.test_oracle.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Goods, Long> {

    @Query("select g from Goods g where g.order=null ")
    List<Goods> getAllGoods();

    @Query("select g from Goods g where g.order!=null ")
    List<Goods> getAllGootsOrder();

    /*
    ToDo
     */
    @Query(value = "select * from goods g where g.order_id=?1", nativeQuery = true)
    List<Goods> getOrdersById(@Param("id") Long id);

//    select * from Goods g where g.order_id=1


    @Query("select g from Goods g where g.goodCode ilike %:text% and g.order=null")
    List<Goods> search(@Param("text") String text);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE goods g SET quantity=? WHERE g.id=:id", nativeQuery = true)
    void updateQuantity(QuantityUpdate quantity, @Param("id") Long id);
}
