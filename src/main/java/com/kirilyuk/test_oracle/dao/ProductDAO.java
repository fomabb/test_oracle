package com.kirilyuk.test_oracle.dao;

import com.kirilyuk.test_oracle.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface ProductDAO extends JpaRepository<Goods, Long> {
}
