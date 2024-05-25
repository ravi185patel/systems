package com.message.publisher.mapper;

import com.message.publisher.entity.Sms;
import com.message.publisher.model.MessageDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
