package com.messagesystem.service;

import com.messagesystem.model.Message;
import reactor.core.publisher.Mono;


public interface MessageReactiveService<T,R> {

    public Mono<R> getMessageId(String id);
    public Mono<R> saveAndPublish(T message);

//
//    @Autowired
//    MessageReactiveDao messageReactiveDao;
//
//    @Autowired
//    private MessageKafkaProducer kafkaProducer;
//
////    @Autowired
////    public MessageReactiveService(MessageKafkaProducer kafkaProducer) {
////        this.kafkaProducer = kafkaProducer;
////    }
//
////
////    public Mono<Message> saveAndPublish(Message message){
////        Mono<Message> savedMessage = messageReactiveDao.save(message);
////        return savedMessage.flatMap(data -> this.kafkaProducer.sendMessage(message.getMessageType(),data));
////    }
//
//    public Mono<Message> getMessageById(String id) {
//        return messageReactiveDao.findById(id);
//    }

}
