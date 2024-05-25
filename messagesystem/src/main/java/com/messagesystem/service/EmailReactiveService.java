package com.messagesystem.service;

import com.messagesystem.dao.SmsReactiveDao;
import com.messagesystem.model.Email;
import com.messagesystem.producer.MessageKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmailReactiveService implements MessageReactiveService<Email,Email>{

    private final SmsReactiveDao smsReactiveDao;
    private final MessageKafkaProducer messageKafkaProducer;

    @Autowired
    public EmailReactiveService(SmsReactiveDao smsReactiveDao, MessageKafkaProducer messageKafkaProducer) {
        this.smsReactiveDao = smsReactiveDao;
        this.messageKafkaProducer = messageKafkaProducer;
    }

    @Override
    public Mono<Email> getMessageId(String id) {
//        return smsReactiveDao.findById(id);
        return Mono.empty();
    }

    @Override
    public Mono<Email> saveAndPublish(Email email) {
        Mono<Email> savedMessage = Mono.empty();//smsReactiveDao.save(sms);
        savedMessage.flatMap(data -> this.messageKafkaProducer.sendMessage(data));
        return savedMessage;
    }
}
