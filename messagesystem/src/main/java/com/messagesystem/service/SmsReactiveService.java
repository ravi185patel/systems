package com.messagesystem.service;

import com.messagesystem.dao.SmsReactiveDao;
import com.messagesystem.model.MessageStatusEnum;
import com.messagesystem.model.Sms;
import com.messagesystem.producer.MessageKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Primary
public class SmsReactiveService implements MessageReactiveService<Sms,Sms>{

    private final SmsReactiveDao smsReactiveDao;
    private final MessageKafkaProducer messageKafkaProducer;

    @Autowired
    public SmsReactiveService(SmsReactiveDao smsReactiveDao,MessageKafkaProducer messageKafkaProducer) {
        this.smsReactiveDao = smsReactiveDao;
        this.messageKafkaProducer = messageKafkaProducer;
    }

    @Override
    public Mono<Sms> getMessageId(String id) {
        return smsReactiveDao.findById(id);
    }

    @Override
    public Mono<Sms> saveAndPublish(Sms sms) {
        sms.setStatus(MessageStatusEnum.INIT);
        Mono<Sms> savedMessage = smsReactiveDao.save(sms);
        savedMessage.flatMap(data -> this.messageKafkaProducer.sendMessage(data));
        return savedMessage;
    }
}
