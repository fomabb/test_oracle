package com.kirilyuk.test_oracle.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
//    @CreationTimestamp
    private LocalDateTime docDate;

//    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
//    @UpdateTimestamp
//    private LocalDateTime updateDocDate;


    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL
            , mappedBy = "orders"
    )
    private List<Goods> goods;

//    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
//    mappedBy = "orders")
//    private Set<Card> card;

    public Orders(LocalDateTime docDate) {
        this.setDocDate(LocalDateTime.now());
    }
}
