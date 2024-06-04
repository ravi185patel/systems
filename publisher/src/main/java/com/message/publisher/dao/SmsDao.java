package com.message.publisher.dao;

import com.message.publisher.entity.Sms;
import com.message.publisher.repository.SmsRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SmsDao implements PublisherDaoInterface<Sms>{

    private static final Logger logger = Logger.getLogger(SmsDao.class);
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
        logger.info("Published message will be save in db");
        sms= smsRepository.save(sms);
        logger.info("Published message successfully saved in db");
        return sms;
    }

    public void deleteById(String id) {
        smsRepository.deleteById(id);
    }

    public void deleteAll() {
        smsRepository.deleteAll();
    }

}
