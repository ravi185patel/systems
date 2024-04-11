package com.messagesystem.controller;

import com.messagesystem.model.Message;
import com.messagesystem.service.MessageReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v2/kafka")
public class ProducerController {


    @Autowired
    MessageReactiveService messageReactiveService;

    @PostMapping("/publish")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Message>> publish(@RequestBody Message message){
        Mono<Message> messageMono=messageReactiveService.saveAndPublish(message);
        return ResponseEntity.ok(messageMono);
    }

    @GetMapping("/message/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Message> getMessageById(@PathVariable("id") String id) {
        return messageReactiveService.getMessageById(id);
    }

}

