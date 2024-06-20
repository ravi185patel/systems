package com.sms.worker.consumer;

import com.sms.worker.model.SmsDto;
import com.sms.worker.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SmsWorker {

    private final SmsService smsService;

    @Autowired
    public SmsWorker(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/publish")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> consume(@RequestBody SmsDto sms){
        Optional<SmsDto> smsDtoOptional=smsService.updateSms(sms);
        return ResponseEntity.ok(smsDtoOptional);
    }

    @KafkaListener(topics="sms",groupId = "group-tenant1-id")
    public void kafkaConsumer(@RequestBody SmsDto sms){
        System.out.println("*************************************** ");
        System.out.println(sms.getMessageId());
        Optional<SmsDto> smsDtoOptional=smsService.updateSms(sms);
        System.out.println("***************************************");
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private void sendSms(@RequestBody SmsDto sms){
        try {
            RestTemplate restTemplate = new RestTemplate();
            final ResponseEntity<String> responseEntity = restTemplate.exchange("https://enterprise.smsgupshup.com/GatewayAPI/rest?method=sendMessage&userid={userid}&password={password}&msg={msg}&send_to={send_to}&v={version}&msg_type={msg_type}&auth_scheme={auth_scheme}",
                    HttpMethod.GET, HttpEntity.EMPTY, String.class,
                    "<userid>", "<password>", "<SMS text>", "<mobile number>", "1.1", "TEXT", "PLAIN");
            final String body = responseEntity.getBody();
            System.out.println("response = " + body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

