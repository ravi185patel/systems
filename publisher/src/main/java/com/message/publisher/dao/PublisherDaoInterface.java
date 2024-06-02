package com.message.publisher.dao;

import java.util.List;
import java.util.Optional;

public interface PublisherDaoInterface<T> {
    List<T> findAll();

    Optional<T> findById(String id);

    T save(T t);

    void deleteById(String t);

    void deleteAll();

}
