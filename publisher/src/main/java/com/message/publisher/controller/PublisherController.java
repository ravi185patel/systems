package com.message.publisher.controller;

import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.model.MessageDto;
import com.message.publisher.service.MessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class PublisherController {

    private final MessageServiceImp messageServiceImp;

    @Autowired
    public PublisherController(MessageServiceImp messageServiceImp) {
        this.messageServiceImp = messageServiceImp;
    }

    @PostMapping("/publish")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageDto> publish(@RequestBody MessageDto message){
        MessageDto messageMono=messageServiceImp.saveAndPublish(message);
        return ResponseEntity.ok(messageMono);
    }

    @GetMapping("/message/{type}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> getMessageById(@PathVariable("type") MessageTypeEnum messageTypeEnum, @PathVariable("id") String id) {
        MessageDto messageMono = messageServiceImp.getMessageById(messageTypeEnum,id);
        return ResponseEntity.ok(messageMono);
    }

}

