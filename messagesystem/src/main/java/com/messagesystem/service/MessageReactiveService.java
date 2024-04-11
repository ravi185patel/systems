package com.messagesystem.service;

import com.messagesystem.dao.MessageReactiveDao;
import com.messagesystem.model.Message;
import com.messagesystem.producer.MessageKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Service
public class MessageReactiveService {

    @Autowired
    MessageReactiveDao messageReactiveDao;

    @Autowired
    private MessageKafkaProducer kafkaProducer;

//    @Autowired
//    public MessageReactiveService(MessageKafkaProducer kafkaProducer) {
//        this.kafkaProducer = kafkaProducer;
//    }


    public Mono<Message> saveAndPublish(Message message){
        Mono<Message> savedMessage = messageReactiveDao.save(message);
        return savedMessage.flatMap(data -> this.kafkaProducer.sendMessage(message.getMessageType(),data));
    }

    public Mono<Message> getMessageById(String id) {
        return messageReactiveDao.findById(id);
    }

}
