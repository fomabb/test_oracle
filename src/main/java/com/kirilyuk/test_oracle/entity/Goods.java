package com.kirilyuk.test_oracle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "goods")
@Getter
@Setter
@NoArgsConstructor
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "good_code")
    private String goodCode;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    @Min(value = 1, message = "product is not exist")
    private long quantity ;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "goods")
    private List<GoodsInOrder> goodsInOrderList;

//*************************************************************

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id")
//    private Orders orders;

}
