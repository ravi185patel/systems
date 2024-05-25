package com.message.publisher.service;

import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.entity.Sms;
import com.message.publisher.model.MessageDto;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MessageServiceImp {
    private final MessageService messageService;
    private final ServiceFactory serviceFactory;

    private final ModelMapper mapper;

    @Autowired
    public MessageServiceImp(MessageService messageService,ServiceFactory serviceFactory,ModelMapper mapper) {
        this.messageService = messageService;
        this.serviceFactory = serviceFactory;
        this.mapper = mapper;
    }

    @PostConstruct
    public void checkObject(){
        System.out.println("***************************** hi there "+(messageService instanceof SmsService) +" "+messageService.hashCode());
    }

    public MessageDto saveAndPublish(MessageDto messageDto){
        MessageService messageService = serviceFactory.getServiceInstance(messageDto.getMessageType());
        Optional<MessageDto> savedMessage = messageService.saveAndPublish(messageDto);
        if(savedMessage.isPresent()){
            return savedMessage.get();
        }else {
            throw new RuntimeException("Error in saving message in db");
        }
    }

    public MessageDto getMessageById(MessageTypeEnum messageType, String id) {
        MessageService messageService = serviceFactory.getServiceInstance(messageType);
        Optional<Sms> savedMessage = messageService.getMessageId(id);
        if(savedMessage.isPresent()){
            MessageDto resMessageDto = mapper.map(savedMessage.get(), MessageDto.class);
            return resMessageDto;
        }else {
            throw new RuntimeException("Error in saving message in db");
        }
    }
}
