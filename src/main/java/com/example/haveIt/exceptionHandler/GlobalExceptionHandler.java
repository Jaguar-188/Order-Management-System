package com.example.haveIt.exceptionHandler;

import com.example.haveIt.entity.exception.*;
import com.example.haveIt.utils.Logging;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logging log = Logging.getLogger();
    @ExceptionHandler(CustomerNotFoundException.class)
    protected ResponseEntity<ExceptionMessage> customerNotFoundExceptionHandler(CustomerNotFoundException customerNotFoundException,
                                                                                WebRequest webRequest){

        log.error("Resolving customer not found exception");
        ExceptionMessage exceptionMessage = new ExceptionMessage(
                customerNotFoundException.getMessage(),
                webRequest.getDescription(false).replace("uri="," "),
                String.valueOf(HttpStatus.NOT_FOUND),
                String.valueOf(HttpStatus.NOT_FOUND).substring(0,3),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    protected ResponseEntity<ExceptionMessage> emailAlreadyExistsExceptionHandler(EmailAlreadyExistsException emailAlreadyExistsException,
                                                                                  WebRequest webRequest){

        log.error("Resolving customer not found exception");
        ExceptionMessage exceptionMessage = new ExceptionMessage(
                emailAlreadyExistsException.getMessage(),
                webRequest.getDescription(false).replace("uri="," "),
                String.valueOf(HttpStatus.NOT_ACCEPTABLE),
                String.valueOf(HttpStatus.NOT_ACCEPTABLE).substring(0,3),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<ExceptionMessage> ItemNotFoundExceptionHandler(ItemNotFoundException itemNotFoundException,
                                                                                WebRequest webRequest){

        log.error("Resolving item not found exception");
        ExceptionMessage exceptionMessage = new ExceptionMessage(
                itemNotFoundException.getMessage(),
                webRequest.getDescription(false).replace("uri="," "),
                String.valueOf(HttpStatus.NOT_FOUND),
                String.valueOf(HttpStatus.NOT_FOUND).substring(0,3),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    protected ResponseEntity<ExceptionMessage> OrderNotFoundExceptionHandler(OrderNotFoundException orderNotFoundException,
                                                                            WebRequest webRequest){

        log.error("Resolving order not found exception");
        ExceptionMessage exceptionMessage = new ExceptionMessage(
                orderNotFoundException.getMessage(),
                webRequest.getDescription(false).replace("uri="," "),
                String.valueOf(HttpStatus.NOT_FOUND),
                String.valueOf(HttpStatus.NOT_FOUND).substring(0,3),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage,HttpStatus.NOT_FOUND);
    }
}
