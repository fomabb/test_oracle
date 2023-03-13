package com.kirilyuk.test_oracle.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private long quantity;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;

}
