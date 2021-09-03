package com.ivan.cayabyab.gofluent.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;
    private String status = "PENDING";
    private long total;
    private int customerId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket", orphanRemoval = true)
    private Set<Item> items;

    @Override
    public String toString(){
        return new StringBuilder("Basket ").append(basketId).append("Customer Id").append(customerId).toString();
    }

}
