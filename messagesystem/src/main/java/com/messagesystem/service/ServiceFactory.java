package com.messagesystem.service;

import com.messagesystem.model.MessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {

    private final SmsReactiveService smsReactiveService;

    private final EmailReactiveService emailReactiveService;

    @Autowired
    public ServiceFactory(SmsReactiveService smsReactiveService,EmailReactiveService emailReactiveService) {
        this.smsReactiveService = smsReactiveService;
        this.emailReactiveService = emailReactiveService;
    }

    public MessageReactiveService getServiceInstance(MessageTypeEnum messageTypeEnum){
        MessageReactiveService messageReactiveService= null;
        switch (messageTypeEnum){
            case SMS -> messageReactiveService = smsReactiveService;
            case EMAIL -> messageReactiveService = emailReactiveService;
            default -> messageReactiveService=smsReactiveService;
        }
        return messageReactiveService;
    }
}
