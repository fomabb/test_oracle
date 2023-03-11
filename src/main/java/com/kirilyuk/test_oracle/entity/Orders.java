package com.kirilyuk.test_oracle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime docDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Set<Goods> goods;

    public void addGoodsToDepartment(Goods goods) {

        if (this.goods == null) {
            this.goods = new HashSet<>();
        }
        this.goods.add(goods);
    }
}
