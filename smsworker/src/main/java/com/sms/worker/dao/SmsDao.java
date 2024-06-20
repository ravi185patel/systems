package com.sms.worker.dao;

import com.sms.worker.constant.MessageStatusEnum;
import com.sms.worker.entity.Sms;
import com.sms.worker.repository.SmsRepository;
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

    public Sms save(Sms Sms) {
        return smsRepository.save(Sms);
    }

    public Optional<Sms> update(Sms sms) {
        return Optional.of(smsRepository.save(sms));
    }

    public void deleteById(String id) {
        smsRepository.deleteById(id);
    }

    public void deleteAll() {
        smsRepository.deleteAll();
    }

}
