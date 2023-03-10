package com.kirilyuk.test_oracle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
//    @CreationTimestamp
    private LocalDateTime docDate;

//    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
//    @UpdateTimestamp
//    private LocalDateTime updateDocDate;


//    @JsonBackReference
//    @OneToMany(cascade = CascadeType.ALL
//            , mappedBy = "orders"
//    )

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Goods> goods;

//    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
//    mappedBy = "orders")
//    private Set<Card> card;

    public Orders(LocalDateTime docDate) {
        this.setDocDate(LocalDateTime.now());
    }

    public void addGoodsToDepartment(Goods goods) {

        if (this.goods == null) {
            this.goods = new ArrayList<>();
        }
        this.goods.add(goods);
    }
}
