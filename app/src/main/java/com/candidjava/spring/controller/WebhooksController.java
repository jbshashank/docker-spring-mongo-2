package com.candidjava.spring.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebhooksController {

	private static final String SIGNATURE = "shashi@github1";
	private static final String SUCCESS = "Success";
	private static final String ERROR = "Error";
	

	 @PostMapping(value="/webhooks",headers="Accept=application/json")
	 public ResponseEntity<Void> Webhook() throws Exception{
		 Process p;
	     HttpHeaders headers = new HttpHeaders();
		  try {
		   String[] cmd = { "sh", "/gitSync.sh"};
		   p = Runtime.getRuntime().exec(cmd); 
		   p.waitFor(); 
		   BufferedReader reader=new BufferedReader(new InputStreamReader(
		    p.getInputStream())); 
		   String line; 
		   while((line = reader.readLine()) != null) { 
		    System.out.println(line);
		   } 
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (InterruptedException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
}
