package com.ecommerce.exception;

import com.ecommerce.common.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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

}
