package com.message.publisher.repository;


import com.message.publisher.entity.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {
    Optional<Email> findById(String id);
}
