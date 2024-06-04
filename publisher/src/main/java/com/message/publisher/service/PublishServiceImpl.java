package com.message.publisher.service;

import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.custome.exception.RecordNotFoundException;
import com.message.publisher.dao.DaoFactory;
import com.message.publisher.dao.PublisherDaoInterface;
import com.message.publisher.entity.Email;
import com.message.publisher.entity.Sms;
import com.message.publisher.mapper.CommonMapper;
import com.message.publisher.model.MessageDto;
import com.message.publisher.producer.MessageKafkaProducer;
import org.apache.kafka.common.KafkaException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class PublishServiceImpl implements PublishService{

    private static final Logger logger = Logger.getLogger(PublishServiceImpl.class);
    private final DaoFactory daoFactory;
    private MessageKafkaProducer messageKafkaProducer;

    private final CommonMapper commonMapper;

    @Autowired
    public PublishServiceImpl(DaoFactory daoFactory,CommonMapper commonMapper,MessageKafkaProducer messageKafkaProducer) {
        this.commonMapper = commonMapper;
        this.daoFactory = daoFactory;
        this.messageKafkaProducer = messageKafkaProducer;
    }

    @Override
    public MessageDto getMessageId(MessageTypeEnum messageTypeEnum,String id) {
        logger.info("Retrive publish message based on type: "+messageTypeEnum.toString()+" and id: "+id);
        PublisherDaoInterface publisherDaoInterface = daoFactory.getDaoInstance(messageTypeEnum);
        Optional<Sms> optionalSms = publisherDaoInterface.findById(id);
        if(optionalSms.isPresent()){
            Sms sms =optionalSms.get();
            logger.info("Present publish message "+sms.toString());
            return commonMapper.smsEntityToDto(sms);
        }else{
            throw new RecordNotFoundException("No publish message found for id:"+id);
        }
    }

    @Override
    public MessageDto publish(MessageDto messageDto) {
        logger.info("Publish message based on type: "+messageDto.getMessageType());
        MessageDto messageDtoRes = null;
        switch (messageDto.getMessageType()){
            case SMS -> messageDtoRes = saveSms(messageDto);
            case EMAIL -> messageDtoRes = saveEmail(messageDto);
        }

        this.messageKafkaProducer.sendMessage(messageDtoRes.getMessageId(), messageDtoRes.getMessageType());
        logger.info("Message published in kafka based on message type: "+messageDtoRes.getMessageType());
        return messageDtoRes;
    }

    private MessageDto saveSms(MessageDto messageDto){
        Sms sms = commonMapper.dtoToSmsEntity(messageDto);
        sms = (Sms) daoFactory.getDaoInstance(messageDto.getMessageType()).save(sms);
        logger.info("Publish message saved : "+sms.toString());
        return commonMapper.smsEntityToDto(sms);
    }

    private MessageDto saveEmail(MessageDto messageDto){
        Email email = commonMapper.dtoToEmailEntity(messageDto);
        email = (Email) daoFactory.getDaoInstance(messageDto.getMessageType()).save(email);
        logger.info("Publish message saved : "+email.toString());
        return commonMapper.emailEntityToDto(email);
    }
}
