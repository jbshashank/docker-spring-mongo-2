package com.candidjava.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.Employee;

import java.util.List;

public interface UserRepository extends MongoRepository<Employee, String> {

    @Query("{ $or: [ { 'employeePersonalDetails.empFirstName' : {$regex:?0,$options:'i'} }, { 'employeePersonalDetails.empLastName' : {$regex:?0,$options:'i'} }, " +
            " { 'employeePersonalDetails.role' : {$regex:?0,$options:'i'} } ] }")
    List<Employee> findByEmpFirstName(final String empFirstName);
}


