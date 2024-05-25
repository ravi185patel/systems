package com.messagesystem.repository;


import com.messagesystem.model.Whatsup;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhatsupRepository extends ReactiveMongoRepository<Whatsup, String> {

}
