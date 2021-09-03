package com.ivan.cayabyab.gofluent.repository;

import com.ivan.cayabyab.gofluent.entity.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer> {

}
