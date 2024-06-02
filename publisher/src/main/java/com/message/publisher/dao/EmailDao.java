package com.message.publisher.dao;

import com.message.publisher.entity.Email;
import com.message.publisher.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmailDao implements PublisherDaoInterface<Email>{

    private final EmailRepository emailRepository;

    @Autowired
    public EmailDao(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public Optional<Email> findById(String id) {
        return emailRepository.findById(id);
    }

    public Email save(Email email) {
        return emailRepository.save(email);
    }

    public void deleteById(String id) {
        emailRepository.deleteById(id);
    }

    public void deleteAll() {
        emailRepository.deleteAll();
    }

}
