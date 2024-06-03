package com.message.publisher.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class CommonResponse {

    @Autowired
    private SuccessResponse successFullResponse;

    public ResponseEntity<Object> getSuccessFullResponse(Object data, boolean flag){
        if(flag) {
            successFullResponse.setCode("200");
            successFullResponse.setStatus("success");
            successFullResponse.setData(data);
            return new ResponseEntity<>(successFullResponse, HttpStatus.OK);
        }else{
            ErrorResponse errorResponse=(ErrorResponse)data;
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }
    }
}