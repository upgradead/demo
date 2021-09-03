package com.ivan.cayabyab.gofluent.exception;

import lombok.Data;

@Data
public class BasketValidationException extends RuntimeException {

    private String message;

    public BasketValidationException(String message){
        this.message = message;
    }

}
