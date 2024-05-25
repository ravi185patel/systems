package com.messagesystem.repository;


import com.messagesystem.model.Sms;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends ReactiveMongoRepository<Sms, String> {

}
