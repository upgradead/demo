package com.ivan.cayabyab.gofluent.controller;

import com.ivan.cayabyab.gofluent.model.Basket;
import com.ivan.cayabyab.gofluent.model.BasketTotal;
import com.ivan.cayabyab.gofluent.model.Item;
import com.ivan.cayabyab.gofluent.model.Product;
import com.ivan.cayabyab.gofluent.service.BasketService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/basket")
@Slf4j
public class BasketController {

    private BasketService basketService;

    private ModelMapper mapper;

    @Autowired
    public BasketController(BasketService basketService, ModelMapper mapper) {
        this.basketService = basketService;
        this.mapper = mapper;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = Basket.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied")})
    @PostMapping(value = "/{customerId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Basket> createBasket(@ApiParam(value = "", required = true) @PathVariable("customerId") Integer customerId) {
        Basket basket = new Basket();
        basket.setBasketId(basketService.createBasket(customerId));
        return ResponseEntity.ok().body(basket);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = Product.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid ID supplied")})
    @GetMapping(value = "/retrieveItems/{basketId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> retrieveItems(@ApiParam(value = "", required = true) @PathVariable("basketId") Integer basketId) {
        List<com.ivan.cayabyab.gofluent.entity.Product> source = basketService.retrieveAll(basketId);
        List<Product> products =  mapper.map(source, new TypeToken<List<Product>>() {}.getType());
        return ResponseEntity.ok().body(products);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied")})
    @PutMapping(value = "/add",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addItem(@ApiParam(value = "", required = true) @Valid @RequestBody Item body) {
        basketService.addItem(body.getBasketId(), body.getProductId(), body.getPieces());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied")})
    @PutMapping(value = "/remove",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> removeItem(@ApiParam(value = "", required = true) @Valid @RequestBody Item body) {
        basketService.removeItem(body.getBasketId(), body.getProductId(), body.getPieces());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = BasketTotal.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Basket or Item not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    @GetMapping(value = "/getTotal/{basketId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BasketTotal> getTotal(@ApiParam(value = "", required = true) @PathVariable("basketId") Integer basketId) {
        BasketTotal total = new BasketTotal();
        total.setTotal(basketService.getTotal(basketId));
        return ResponseEntity.ok().body(total);
    }
}
