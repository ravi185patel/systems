package com.message.publisher.service;
import java.util.Optional;

public interface MessageService<T> {

    public Optional<T> getMessageId(String id);
    public Optional<T> saveAndPublish(T message);

}
