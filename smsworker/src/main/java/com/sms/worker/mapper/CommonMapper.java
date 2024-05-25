package com.sms.worker.mapper;

import com.sms.worker.entity.Sms;
import com.sms.worker.model.SmsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonMapper {
    @Autowired
    private ModelMapper modelMapper;


    public Sms dtoToSmsEntity(SmsDto smsDto) {
        return modelMapper.map(smsDto, Sms.class);
    }

    public SmsDto smsEntityToDto(Sms sms) {
        return modelMapper.map(sms, SmsDto.class);
    }
}
