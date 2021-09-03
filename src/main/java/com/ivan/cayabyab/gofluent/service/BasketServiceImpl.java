package com.ivan.cayabyab.gofluent.service;

import com.ivan.cayabyab.gofluent.entity.Basket;
import com.ivan.cayabyab.gofluent.entity.Item;
import com.ivan.cayabyab.gofluent.entity.Product;
import com.ivan.cayabyab.gofluent.exception.BasketValidationException;
import com.ivan.cayabyab.gofluent.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Override
    @Transactional
    public int createBasket(int customerId) {
        Basket basket = new Basket();
        basket.setCustomerId(customerId);
        basket.setItems(Collections.emptySet());

        Basket save = basketRepository.save(basket);

        return save.getBasketId();
    }

    @Override
    @Transactional
    public void addItem(int basketId, int productId, int pieces) {

        Optional<Basket> byId = basketRepository.findById(basketId);

        if (!byId.isPresent()) throw new BasketValidationException("Not found");

        byId.ifPresent( basket -> {
            Optional<Item> any = basket.getItems().stream().filter(x -> x.getProductId() == productId).findAny();

            if (any.isPresent()) {
                any.ifPresent(x -> x.setPieces(x.getPieces() + pieces));
            } else {
                basket.getItems().add(Item.builder().productId(productId).pieces(pieces).basket(basket).build());
            }
            basketRepository.save(basket);
        });
    }

    @Transactional
    @Override
    public void removeItem(int basketId, int productId, int pieces) {

        Optional<Basket> byId = basketRepository.findById(basketId);
        if (!byId.isPresent()) throw new BasketValidationException("Not found");

        byId.ifPresent(
                basket -> {
                    basket.getItems().removeIf(x -> x.getProductId() == productId);
                    basketRepository.save(basket);
                }
        );
    }

    @Override
    public List<Product> retrieveAll(int basketId) {
        Optional<Basket> byId = basketRepository.findById(basketId);

        if (!byId.isPresent()) throw new BasketValidationException("Not found");

        return byId.get().getItems().stream().map(x -> x.getProduct()).collect(Collectors.toList());
    }

    @Override
    public double getTotal(int basketId) {
        Optional<Basket> byId = basketRepository.findById(basketId);

        if (!byId.isPresent()) throw new BasketValidationException("Not found");

        return byId.get().getItems().stream().mapToDouble(x -> x.getProduct().getPrice()).sum();
    }
}
