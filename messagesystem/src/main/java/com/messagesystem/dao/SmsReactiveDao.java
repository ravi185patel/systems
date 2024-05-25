package com.messagesystem.dao;

import com.messagesystem.model.Sms;
import com.messagesystem.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class SmsReactiveDao {

    private final SmsRepository smsRepository;

    @Autowired
    public SmsReactiveDao(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    public Flux<Sms> findAll() {
        return smsRepository.findAll();
    }

    public Mono<Sms> findById(String id) {
        return smsRepository.findById(id);
    }

    public Mono<Sms> save(Sms Sms) {
        return smsRepository.save(Sms);
    }

    public Mono<Sms> update(String id, Sms Sms) {
        return smsRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalSms -> {
                    if (optionalSms.isPresent()) {
                        Sms.setId(id);
                        return smsRepository.save(Sms);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(String id) {
        return smsRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return smsRepository.deleteAll();
    }

}
