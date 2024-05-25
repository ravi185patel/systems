package com.message.publisher.service;

import com.message.publisher.constant.MessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {

    private final SmsService smsService;

    @Autowired
    public ServiceFactory(SmsService smsService) {
        this.smsService = smsService;
    }

    public MessageService getServiceInstance(MessageTypeEnum messageTypeEnum){
        MessageService messageService= null;
        switch (messageTypeEnum){
            case SMS -> messageService = smsService;
            default -> messageService=smsService;
        }
        return messageService;
    }
}
