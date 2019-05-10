package com.candidjava.spring.repository;

import com.candidjava.spring.bean.States;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StateRepository extends MongoRepository<States, String> {

}
