package com.sms.worker.service;

import com.sms.worker.constant.MessageStatusEnum;
import com.sms.worker.constant.MessageTypeEnum;
import com.sms.worker.consumer.SmsKafkaWorker;
import com.sms.worker.dao.SmsDao;
import com.sms.worker.entity.Sms;
import com.sms.worker.mapper.CommonMapper;
import com.sms.worker.model.SmsDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class SmsService{

    private final SmsDao smsDao;
    private final CommonMapper commonMapper;

    private static final Logger logger = LogManager.getLogger(SmsKafkaWorker.class);
    @Autowired
    public SmsService(SmsDao smsDao,CommonMapper commonMapper) {
        this.smsDao = smsDao;
        this.commonMapper = commonMapper;
    }

    
    public Optional<SmsDto> getMessageId(String id) {
        logger.info("Get message based on id :"+id);
        Optional<Sms> optionalSms = smsDao.findById(id);
        if(optionalSms.isPresent()){
            SmsDto SmsDto = commonMapper.smsEntityToDto(optionalSms.get());
            logger.info("Found message based on id :"+id);
            return Optional.of(SmsDto);
        }else{
            logger.info("No Record found for id :"+id);
            throw new RuntimeException("No Record found for id:"+id);
        }
    }

    public Optional<SmsDto> updateSms(String messageId) {
        Optional<SmsDto> optionalSmsDto = getMessageId(messageId);
        return updateSms(optionalSmsDto.get());
    }
    
    public Optional<SmsDto> updateSms(SmsDto smsDto) {
        logger.info("Search Published message based on message id  :"+smsDto.getMessageId());
        Sms updateSms = commonMapper.dtoToSmsEntity(smsDto);
        updateSms.setStatus(MessageStatusEnum.RECEIVED);
        Optional<Sms> updatedOptionalSms = smsDao.update(updateSms);
        Optional<SmsDto> updatedOptionalSmsDto = Optional.of(commonMapper.smsEntityToDto(updatedOptionalSms.get()));
        logger.info("Updated status of publish message : "+updatedOptionalSmsDto.get().getMessageId());
        return updatedOptionalSmsDto;
    }
}
