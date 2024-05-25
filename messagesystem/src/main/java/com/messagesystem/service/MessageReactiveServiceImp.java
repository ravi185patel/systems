package com.messagesystem.service;

import com.messagesystem.model.Message;
import com.messagesystem.model.MessageTypeEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageReactiveServiceImp {
    private final MessageReactiveService messageReactiveService;
    private final ServiceFactory serviceFactory;

    @Autowired
    public MessageReactiveServiceImp(MessageReactiveService messageReactiveService,ServiceFactory serviceFactory) {
        this.messageReactiveService = messageReactiveService;
        this.serviceFactory = serviceFactory;
    }

    @PostConstruct
    public void checkObject(){
        System.out.println("***************************** hi there "+(messageReactiveService instanceof SmsReactiveService) +" "+messageReactiveService.hashCode());
    }

    public Mono<Message> saveAndPublish(Message message){
        MessageReactiveService messageReactiveService = serviceFactory.getServiceInstance(message.getMessageType());
        Mono<Message> savedMessage = messageReactiveService.saveAndPublish(message);
        return  savedMessage;
    }

    public Mono<Message> getMessageById(MessageTypeEnum messageType,String id) {
        MessageReactiveService messageReactiveService = serviceFactory.getServiceInstance(messageType);
        return messageReactiveService.getMessageId(id);
    }
}
