package com.message.publisher.producer;


import com.message.publisher.constant.MessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageKafkaProducer {


    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public MessageKafkaProducer(KafkaTemplate<String,String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String messageId, MessageTypeEnum messageTypeEnum){
        String topic= messageTypeEnum.toString();
        Message<String> messageCon = MessageBuilder
                .withPayload(messageId)
                .setHeader(KafkaHeaders.TOPIC,topic)
                .build();
        this.kafkaTemplate.send(messageCon);
        System.out.println("sending to Kafka "+ messageCon.toString());
    }
}
