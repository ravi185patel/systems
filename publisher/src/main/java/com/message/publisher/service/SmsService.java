package com.message.publisher.service;

import com.message.publisher.constant.MessageStatusEnum;
import com.message.publisher.dao.SmsDao;
import com.message.publisher.entity.Sms;
import com.message.publisher.mapper.CommonMapper;
import com.message.publisher.model.MessageDto;
import com.message.publisher.producer.MessageKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class SmsService implements MessageService<MessageDto>{

    private final SmsDao smsDao;

    private MessageKafkaProducer messageKafkaProducer;

    private final CommonMapper commonMapper;

    @Autowired
    public SmsService(SmsDao smsDao,CommonMapper commonMapper,MessageKafkaProducer messageKafkaProducer) {
        this.commonMapper = commonMapper;
        this.smsDao = smsDao;
        this.messageKafkaProducer = messageKafkaProducer;
    }

    @Override
    public Optional<MessageDto> getMessageId(String id) {
        Optional<Sms> optionalSms = smsDao.findById(id);
        if(optionalSms.isPresent()){
            MessageDto messageDto = commonMapper.smsEntityToDto(optionalSms.get());
            return Optional.of(messageDto);
        }else{
            throw new RuntimeException("No Record found for id:"+id);
        }
    }

    @Override
    public Optional<MessageDto> saveAndPublish(MessageDto messageDto) {
        Sms sms = commonMapper.dtoToSmsEntity(messageDto);
        sms.setStatus(MessageStatusEnum.INIT);
        sms = smsDao.save(sms);
        this.messageKafkaProducer.sendMessage(sms.getMessageId(),sms.getMessageType());
        Optional<MessageDto> optionalMessageDto = Optional.of(commonMapper.smsEntityToDto(sms));
//        savedMessage.flatMap(data -> this.messageKafkaProducer.sendMessage(data));
        return optionalMessageDto;
    }
}
