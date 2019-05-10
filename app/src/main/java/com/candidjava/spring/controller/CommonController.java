package com.candidjava.spring.controller;


import com.candidjava.spring.bean.Cities;

import com.candidjava.spring.bean.Employee;
import com.candidjava.spring.bean.States;
import com.candidjava.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value={"/","/utils"})
public class CommonController {
	

    @Autowired
    UserService userService;


    /*
      Api name: Get all states
      Parameters: N/A
  	*/
    @GetMapping(value="/getstates", headers="Accept=application/json")
    public List<States> getAllState() {
        List<States> tasks=userService.getStates();
        return tasks;

    }

    /*
      Api name: Get all Cities
      Parameters: N/A
  	*/
    @GetMapping(value="/getAllcity", headers="Accept=application/json")
    public List<Cities> getAllCity() {
        List<Cities> tasks=userService.getCities();
        return tasks;

    }

    /*
      Api name: Get city by state id
      Parameters: id
  	*/
    @GetMapping(value="/{id}/getcity", headers="Accept=application/json")
    public List<Cities> getAllCity(@PathVariable("id") int stateId) {
        try{
            List<Cities> tasks=userService.getCityByState(stateId);
            //List<Cities> tasks=userService.getCities();
            return tasks;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
