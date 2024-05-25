package com.messagesystem.dao;

import com.messagesystem.model.Message;
import com.messagesystem.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class MessageReactiveDao {

    @Autowired
    MessageRepository messageRepository;

    public Flux<Message> findAll() {
        return messageRepository.findAll();
    }

    public Mono<Message> findById(String id) {
        return messageRepository.findById(id);
    }

    public Mono<Message> save(Message message) {
        return messageRepository.save(message);
    }

    public Mono<Message> update(String id, Message message) {
        return messageRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalMessage -> {
                    if (optionalMessage.isPresent()) {
                        message.setId(id);
                        return messageRepository.save(message);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(String id) {
        return messageRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return messageRepository.deleteAll();
    }

}
