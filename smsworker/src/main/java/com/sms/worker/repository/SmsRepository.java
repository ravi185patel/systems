package com.sms.worker.repository;

import com.sms.worker.entity.Sms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsRepository extends MongoRepository<Sms, String> {
    Optional<Sms> findById(String id);
}
