package com.candidjava.spring.repository;

import com.candidjava.spring.bean.Cities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface CityRepository extends MongoRepository<Cities, String> {

    @Query("{'stateId': ?0}")
    List<Cities> findByStateId(final int stateId);

}
