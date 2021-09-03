package com.ivan.cayabyab.gofluent.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Product {

    @Id
    private int productId;

    @OneToOne(mappedBy = "product")
    private Item user;

    private String name;
    private double price;

    @Override
    public String toString (){
        return new StringBuilder("Product ")
                .append(productId)
                .append( " Name ").append(name)
                .append(" Price").append(price).toString();
    }
}
