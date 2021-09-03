package com.ivan.cayabyab.gofluent.controller;

import com.ivan.cayabyab.gofluent.exception.BasketValidationException;
import com.ivan.cayabyab.gofluent.model.Basket;
import com.ivan.cayabyab.gofluent.service.BasketService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BasketControllerTest {

    private static BasketService basketService;
    private static ModelMapper modelMapper;

    private static BasketController controller;

    @BeforeClass
    public static void init() {
        basketService = Mockito.mock(BasketService.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        controller = new BasketController(basketService, modelMapper);
    }

    @Test
    public void createNewBasketThenReturnBasketId() {

        Mockito.when(basketService.createBasket(Mockito.anyInt())).thenReturn(5);
        ResponseEntity<Basket> basket = controller.createBasket(1);

        Integer basketId = basket.getBody().getBasketId();
        Assert.assertEquals(5, basketId.intValue());
        Assert.assertEquals(HttpStatus.OK, basket.getStatusCode());
        Mockito.verify(basketService, Mockito.times(1)).createBasket(Mockito.anyInt());
    }

    @Test
    public void whenRetrieveAllThrowsException() {
        Mockito.when(basketService.retrieveAll(Mockito.anyInt())).thenThrow(BasketValidationException.class);
        try {
            controller.retrieveItems(1);
            Assert.fail();
        } catch (BasketValidationException e) {
            Assert.assertNotNull(e);
        }
        Mockito.verify(basketService, Mockito.times(1)).retrieveAll(Mockito.anyInt());
    }

}