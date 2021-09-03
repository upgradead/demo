package com.ivan.cayabyab.gofluent.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Builder
public class Item {

    @Id
    @GeneratedValue
    private int itemId;

    private int productId;
    private int pieces;

    @ManyToOne(fetch = FetchType.LAZY)
    private Basket basket;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Item)) {
            return false;
        }

        Item i = (Item) object;

        return this.getBasket().getBasketId() == i.getBasket().getBasketId() &&
                this.getProductId() == i.getProductId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, productId, pieces);
    }

    @Override
    public String toString() {
        return new StringBuilder("Item ").append(itemId)
                .append(" ProductId ").append(productId).toString();
    }
}
