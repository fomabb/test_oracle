package com.kirilyuk.test_oracle.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "goods_in_order")
@Getter
@Setter
@NoArgsConstructor
public class GoodsInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Orders orders;

    @ManyToOne
    private Goods goods;
}
