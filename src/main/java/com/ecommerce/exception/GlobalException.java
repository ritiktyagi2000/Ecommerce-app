package com.ecommerce.exception;

import com.ecommerce.common.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
@ResponseStatus
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiResponse resourceNotFoundException(DataIntegrityViolationException exception) {
        ApiResponse response = new ApiResponse(false,"Please enter unique product name");
return response;
    }

    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String>handleCustomException(CustomException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = AuthenticationFailedException.class)
    public final ResponseEntity<String>handleAuthenticationFailedException(AuthenticationFailedException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ProductNotFoundException.class)
    public final ResponseEntity<String>handleProductNotFoundException(ProductNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
