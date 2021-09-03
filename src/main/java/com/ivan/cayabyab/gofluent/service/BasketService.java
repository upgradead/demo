package com.ivan.cayabyab.gofluent.service;

import com.ivan.cayabyab.gofluent.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketService {

    int createBasket(int customerId);

    void addItem(int basketId, int productId, int pieces);

    void removeItem(int basketId, int productId, int pieces);

    List<Product> retrieveAll (int basketId);

    double getTotal (int basketId);
}
