package com.message.publisher.dao;

import com.message.publisher.entity.Sms;
import com.message.publisher.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SmsDao {

    private final SmsRepository smsRepository;

    @Autowired
    public SmsDao(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    public List<Sms> findAll() {
        return smsRepository.findAll();
    }

    public Optional<Sms> findById(String id) {
        return smsRepository.findById(id);
    }

    public Sms save(Sms sms) {
        return smsRepository.save(sms);
    }

    public void deleteById(String id) {
        smsRepository.deleteById(id);
    }

    public void deleteAll() {
        smsRepository.deleteAll();
    }

}
