package com.message.publisher.controller;

import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.model.MessageDto;
import com.message.publisher.service.PublishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1")
public class PublisherController {

    private static final Logger logger = LogManager.getLogger(PublisherController.class);
    private final PublishService publishService;

    @Autowired
    public PublisherController(PublishService publishService) {
        this.publishService = publishService;
    }

    @PostMapping("/publish")
    public ResponseEntity<MessageDto> publish(@RequestBody @Valid MessageDto message){
        logger.info("Received publish message at controller "+message.toString());
        MessageDto messageDto=publishService.publish(message);
        logger.info("Message successfully published with reference no: "+messageDto.getMessageId()+" , status: "+messageDto.getStatus());
        return ResponseEntity.ok(messageDto);
    }

    @GetMapping("/message/{type}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> getMessage(@PathVariable("type") MessageTypeEnum messageTypeEnum, @PathVariable("id") String id) {
        MessageDto messageMono = publishService.getMessageId(messageTypeEnum,id);
        return ResponseEntity.ok(messageMono);
    }

}

