package com.messagesystem.controller;

import com.messagesystem.model.Message;
import com.messagesystem.model.MessageTypeEnum;
import com.messagesystem.service.MessageReactiveService;
import com.messagesystem.service.MessageReactiveServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v2/kafka")
public class ProducerController {

    private final MessageReactiveService messageReactiveService;
    private final MessageReactiveServiceImp messageReactiveServiceImp;

    @Autowired
    public ProducerController(MessageReactiveService messageReactiveService,MessageReactiveServiceImp messageReactiveServiceImp) {
        this.messageReactiveService = messageReactiveService;
        this.messageReactiveServiceImp = messageReactiveServiceImp;
    }

    @PostMapping("/publish")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Message>> publish(@RequestBody Message message){
        Mono<Message> messageMono=messageReactiveServiceImp.saveAndPublish(message);
        return ResponseEntity.ok(messageMono);
    }

    @GetMapping("/message/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Message> getMessageById(@PathVariable("type") MessageTypeEnum messageTypeEnum, @PathVariable("id") String id) {
        return messageReactiveServiceImp.getMessageById(messageTypeEnum,id);
    }

}

