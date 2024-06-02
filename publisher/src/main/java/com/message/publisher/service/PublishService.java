package com.message.publisher.service;
import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.model.MessageDto;

public interface PublishService {

    public MessageDto getMessageId(MessageTypeEnum messageTypeEnum, String id);

    public MessageDto publish(MessageDto message);

}