package com.message.publisher.custome.exception;

public class KafkaCustomException extends RuntimeException {
    public KafkaCustomException(String exception){
        super(exception);
    }
}
