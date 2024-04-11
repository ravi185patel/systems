package com.messagesystem.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageKafkaProducer {


    private KafkaTemplate<String, com.messagesystem.model.Message> kafkaTemplate;

    @Autowired
    public MessageKafkaProducer(KafkaTemplate<String, com.messagesystem.model.Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    public void sendMessage(Customer customer){
//        String topic= "";
//
//        Message<Customer> message = MessageBuilder
//                                    .withPayload(customer)
//                                    .setHeader(KafkaHeaders.TOPIC,"fourth_topic_json")
//                                    .build();
//
//        this.kafkaTemplate.send(message);
//    }

    public Mono<com.messagesystem.model.Message> sendMessage(String source, com.messagesystem.model.Message message){
        String topic= "";

        if(source.equalsIgnoreCase("whatsup")){
            topic = "whatsup";
        }else if(source.equalsIgnoreCase("text")){
            topic = "textmessage";
        }else{
            topic="email";
        }

//        Message<com.messagesystem.model.Message> messageCon = MessageBuilder
//                .withPayload(message)
//                .setHeader(KafkaHeaders.TOPIC,topic)
//                .build();

        Message<com.messagesystem.model.Message> messageCon = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC,topic)
                .build();
        this.kafkaTemplate.send(messageCon);
        System.out.println("sending to Kafka "+ source + message.toString());
        return Mono.just(message);
    }

//
//    public void sendMessage(com.example.demo.model.Message message){
//        Message<Customer> message = MessageBuilder
//                .withPayload(customer)
//                .setHeader(KafkaHeaders.TOPIC,"fourth_topic_json")
//                .build();
//
//        this.kafkaTemplate.send(message);
//        if(message.getMessageType().equalsIgnoreCase("whatsup")){
//            kafkaTemplate.send("whatsup",message);
//        }else if(message.getMessageType().equalsIgnoreCase("text")){
//            kafkaTemplate.send("textmessage",message);
//        }else{
//            kafkaTemplate.send("email",message);
//        }
//    }

}
