package com.sms.worker.consumer;

import com.sms.worker.model.SmsDto;
import com.sms.worker.service.SmsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class SmsKafkaWorker {

    private final SmsService smsService;

    private static final Logger logger = LogManager.getLogger(SmsKafkaWorker.class);

    @Autowired
    public SmsKafkaWorker(SmsService smsService) {
        this.smsService = smsService;
    }

    @KafkaListener(topics="SMS",groupId = "group-tenant1-id")
    public void kafkaConsumer(@RequestBody String messageId){
        logger.info(" Publish messaged consumed by Sms worker from kafka");
        Optional<SmsDto> smsDtoOptional=smsService.updateSms(messageId);
        logger.info(" Publish messaged successfully consumed. "+smsDtoOptional.get().toString());
    }
}

