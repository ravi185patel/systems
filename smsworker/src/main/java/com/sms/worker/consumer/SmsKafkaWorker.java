package com.sms.worker.consumer;

import com.sms.worker.model.SmsDto;
import com.sms.worker.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class SmsKafkaWorker {

    private final SmsService smsService;

    @Autowired
    public SmsKafkaWorker(SmsService smsService) {
        this.smsService = smsService;
    }


    @KafkaListener(topics="SMS",groupId = "group-tenant1-id")
    public void kafkaConsumer(@RequestBody String messageId){
        System.out.println("*************************************** ");
        SmsDto sms = new SmsDto();
        sms.setMessageId(messageId);
        System.out.println(sms.getMessageId());
        Optional<SmsDto> smsDtoOptional=smsService.updateSms(sms);
        System.out.println("***************************************");
    }
}

