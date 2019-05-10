package com.candidjava.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.candidjava.spring.bean.Employee;
import com.candidjava.spring.service.UserService;
import com.mongodb.MongoClient;

@RestController
@RequestMapping(value={"/","/user"})
public class UserController {
	
    private final Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	UserService userService;
    @GetMapping("/")
    public String index(Model model) {

        
        return "index";
    }

	/*
		Api name: Get user by id
		Parameters: id
	*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getUserById(@PathVariable("id") String id) {
        System.out.println("Fetching User with id " + id);
        Employee user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(user, HttpStatus.OK);
    }

    /*
		Api name: Create user
		Parameters: Employee Object
	*/
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createUser(@RequestBody Employee user, UriComponentsBuilder ucBuilder){
	     //System.out.println("Creating User "+user.getName());
	     userService.createUser(user);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	/*
       Api name: Image upload
       Parameters: file
   */
	@PostMapping(value="/upload")
	public ResponseEntity<Map<String,String>> fileUpload(@RequestParam("file") MultipartFile uploadfile){
		//System.out.println("Creating User "+user.getName());
		String filePath = userService.uploadImage(uploadfile);
		Map<String, String> imageProp = new HashMap<String, String>();
		imageProp.put("path", filePath);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Map<String,String>>(imageProp, HttpStatus.OK);
	}

	/*
      Api name: Get all user
      Parameters: N/A
  	*/
	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Employee> getAllUser() {	 
	  List<Employee> tasks=userService.getUser();
	  return tasks;
	
	 }

	 /*
      Api name: Update user
      Parameters: Employee Object
  	*/
	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody Employee currentUser)
	{
		System.out.println("sd");
		Employee user = userService.findById(currentUser.getId());
	if (user==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	userService.update(currentUser, currentUser.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}

	/*
      Api name: Delete user
      Parameters: id
  	*/
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Employee> deleteUser(@PathVariable("id") String id){
		Employee user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

	/*
      Api name: Update user partially
      Parameters: id, Employee Object
  	*/
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<Employee> updateUserPartially(@PathVariable("id") String id, @RequestBody Employee currentUser){
		Employee user = userService.findById(id);
		if(user ==null){
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		Employee usr =	userService.updatePartially(currentUser, id);
		return new ResponseEntity<Employee>(usr, HttpStatus.OK);
	}

	/*
      Api name: Search by First Name, Last Name Or Role
      Parameters: searchParam
  	*/
	@GetMapping(value="/search", headers="Accept=application/json")
	public List<Employee> searchByName(@RequestParam("searchParam") String searchParam){
		List<Employee> user = userService.findByName(searchParam);
		return user;
	}
}
