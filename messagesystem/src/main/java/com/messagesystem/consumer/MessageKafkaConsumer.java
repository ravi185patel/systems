package com.messagesystem.consumer;

import com.messagesystem.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageKafkaConsumer {

    @KafkaListener(topics="whatsup",groupId = "group-tenant1-id")
    public void consumerWhatsup(Message message){
        System.out.println("***************************************");
        System.out.println("whatsup "+ message.toString());
        System.out.println("***************************************");
    }

    @KafkaListener(topics="textmessage",groupId = "group-tenant1-id")
    public void consumerText(Message message){
        System.out.println("***************************************");
        System.out.println("textmessage "+message.toString());
        System.out.println("***************************************");
    }

    @KafkaListener(topics="email",groupId = "group-tenant1-id")
    public void consumerEmail(Message message){
        System.out.println("***************************************");
        System.out.println("email "+message.toString());
        System.out.println("***************************************");
    }
}

