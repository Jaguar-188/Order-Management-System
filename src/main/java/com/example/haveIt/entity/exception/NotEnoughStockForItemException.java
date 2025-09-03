package com.example.haveIt.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
public class NotEnoughStockForItemException extends RuntimeException{

    public NotEnoughStockForItemException(String message) {
        super(message);
    }
}
