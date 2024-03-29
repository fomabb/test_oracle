package com.kirilyuk.test_oracle.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "goods")
@Data
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
    private int quantity;

    @JsonBackReference
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;

    public Goods(String goodCode, double price, int quantity) {
        this.goodCode = goodCode;
        this.price = price;
        this.quantity = quantity;
    }

    public Goods(int quantity) {
        this.quantity = quantity;
    }
}
