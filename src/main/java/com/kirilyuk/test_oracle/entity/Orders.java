package com.kirilyuk.test_oracle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Goods> goods;

    public void addGoodsToDepartment(Goods good) {

        if (goods == null) {
            goods = new HashSet<>();
        }
        goods.add(good);
        good.setOrder(this);
    }

//    public void removeGoods(Goods good) {
//
//        goods.remove(good);
//        good.setOrder(null);
//    }
}
