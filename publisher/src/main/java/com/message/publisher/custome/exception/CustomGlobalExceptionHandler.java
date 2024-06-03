package com.message.publisher.custome.exception;

import com.message.publisher.model.ErrorResponse;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorResponse errorResponse;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){

        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        errorResponse.setMessage("Validation failed.");
        errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setErrors(details);
//        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<Object> handleNoRecordFound(RecordNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        body.put("error", errorResponse);
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        body.put("error", errorResponse);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }



}
