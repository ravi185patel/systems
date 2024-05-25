package com.messagesystem.repository;


import com.messagesystem.model.Email;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends ReactiveMongoRepository<Email, String> {

}
