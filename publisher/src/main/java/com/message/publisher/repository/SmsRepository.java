package com.message.publisher.repository;


import com.message.publisher.entity.Sms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsRepository extends MongoRepository<Sms, String> {
    Optional<Sms> findById(String id);
}
