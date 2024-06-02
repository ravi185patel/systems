package com.message.publisher.mapper;

import com.message.publisher.entity.Email;
import com.message.publisher.entity.Sms;
import com.message.publisher.model.MessageDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class CommonMapper{
    @Autowired
    private ModelMapper modelMapper;


    public Sms dtoToSmsEntity(MessageDto messageDto) {
        return modelMapper.map(messageDto, Sms.class);
    }

    public MessageDto smsEntityToDto(Sms sms) {
        return modelMapper.map(sms, MessageDto.class);
    }

    public Email dtoToEmailEntity(MessageDto messageDto) {
        return modelMapper.map(messageDto, Email.class);
    }

    public MessageDto emailEntityToDto(Email email) {
        return modelMapper.map(email, MessageDto.class);
    }

    public Type dtoToEntity(MessageDto messageDto, Type type) {
        return modelMapper.map(messageDto, type);
    }

    public MessageDto entityToDto(Object object) {
        return modelMapper.map(object, MessageDto.class);
    }

}
