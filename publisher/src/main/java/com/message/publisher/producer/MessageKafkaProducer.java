package com.message.publisher.producer;


import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.custome.exception.KafkaCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class MessageKafkaProducer {

    private static final Logger logger = LogManager.getLogger(MessageKafkaProducer.class);

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
        try {
            this.kafkaTemplate.send(messageCon);
        }catch (Exception ex){
            logger.error("kafka is down/some issue on kafka side. Exception: "+ex.getMessage());
            throw new KafkaCustomException("kafka is down/some issue on kafka side.");
        }

        logger.info("sending to Kafka "+ messageCon.toString());
    }
}
