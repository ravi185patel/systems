package com.sms.worker.service;

import com.sms.worker.constant.MessageStatusEnum;
import com.sms.worker.constant.MessageTypeEnum;
import com.sms.worker.dao.SmsDao;
import com.sms.worker.entity.Sms;
import com.sms.worker.mapper.CommonMapper;
import com.sms.worker.model.SmsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class SmsService{

    private final SmsDao smsDao;
    private final CommonMapper commonMapper;

    @Autowired
    public SmsService(SmsDao smsDao,CommonMapper commonMapper) {
        this.smsDao = smsDao;
        this.commonMapper = commonMapper;
    }

    
    public Optional<SmsDto> getMessageId(String id) {
        Optional<Sms> optionalSms = smsDao.findById(id);
        if(optionalSms.isPresent()){
            SmsDto SmsDto = commonMapper.smsEntityToDto(optionalSms.get());
            return Optional.of(SmsDto);
        }else{
            throw new RuntimeException("No Record found for id:"+id);
        }
    }

    
    public Optional<SmsDto> updateSms(SmsDto smsDto) {
        Sms updateSms = commonMapper.dtoToSmsEntity(smsDto);
        Optional<Sms> updatedOptionalSms = smsDao.update(updateSms);
        Optional<SmsDto> updatedOptionalSmsDto = Optional.of(commonMapper.smsEntityToDto(updatedOptionalSms.get()));
        return updatedOptionalSmsDto;
    }
}
