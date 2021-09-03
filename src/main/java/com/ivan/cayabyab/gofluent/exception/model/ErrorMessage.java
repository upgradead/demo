package com.ivan.cayabyab.gofluent.exception.model;

import lombok.Data;

@Data
public class ErrorMessage {

    private String errorCode;
    private String errorMessage;

    public ErrorMessage(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
