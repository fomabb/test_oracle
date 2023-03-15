package com.kirilyuk.test_oracle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime docDate;

    @OneToMany(mappedBy = "order"
            , cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , fetch = FetchType.EAGER)
    private List<Goods> goods = new ArrayList<>();

    public void addGoodsToOrder(Goods good) {

        if (goods == null) {
            goods = new ArrayList<>();
        }
        goods.add(good);
        good.setOrder(this);
    }

    public void deleteToOrder(Goods good) {
        goods.remove(good);
        good.setOrder(this);
    }
}
