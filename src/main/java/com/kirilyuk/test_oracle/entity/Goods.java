package com.kirilyuk.test_oracle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
//    @Size(min = 1, message = "product is not exist")
    @Min(value = 1, message = "product is not exist")
    private long quantity ;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id")
//    private Orders orders;

}
