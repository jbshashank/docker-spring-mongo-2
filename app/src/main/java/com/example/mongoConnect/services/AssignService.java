package com.example.mongoConnect.services;


import java.util.Collection;

import org.json.JSONArray;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@RestController
@RequestMapping("/hell")
public class AssignService {
	
	public JSONArray getAllTechInArea(String pincode, String producttype){
		MongoClient client = new MongoClient("localhost", 27017);
		DB dbs = client.getDB("ayushya");
		DBCollection userCollection = dbs.getCollection("Users");
		DBCursor userCur = userCollection.find();
		JSONArray allUsersInArea = new JSONArray();
		while(userCur.hasNext()) {
			DBObject user = userCur.next();
			if(user.get("pin").equals(pincode) && user.get("expert_in").toString().equalsIgnoreCase(producttype))
				allUsersInArea.put(user);
		}
		userCur = userCollection.find();
		while(userCur.hasNext()) {
			System.out.println(userCur.next());
		}
		if(!allUsersInArea.isEmpty()) {
			return allUsersInArea;
		}
		return null;
	}
	
	public String getUserWithMinimumTickets(JSONArray userArray) {
		int min = 100;
		String employeeID = null;
		for(int i = 0;i < userArray.length();i++) {
			BasicDBObject userObject = (BasicDBObject) userArray.get(i);
			if(Integer.parseInt(userObject.get("no_assigned").toString()) < min) {
				min = Integer.parseInt(userObject.get("no_assigned").toString());
				employeeID = userObject.get("_id").toString();
			}
		}
		updateNoOfTicketsAssignedToUser(employeeID, min);
		System.out.println(employeeID);
		if(!employeeID.isEmpty()) {
			return employeeID;
		}
		return null;
	}
	
	public void updateNoOfTicketsAssignedToUser(String user, int present) {
		MongoClient client = new MongoClient("localhost", 27017);
		DB dbs = client.getDB("ayushya");
		DBCollection userCollection = dbs.getCollection("Users");
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.append("$set", new BasicDBObject().append("no_assigned", ++present));
		BasicDBObject old = new BasicDBObject().append("_id", user);
		userCollection.update(old, updateObject, false, false);
		System.out.println("updated");
	}

//	@Cacheable(value = "technician")
	@RequestMapping(value = "/cache", method = RequestMethod.GET)
	public String getTech() {
		MongoClient client = new MongoClient("localhost", 27017);
		DB dbs = client.getDB("ayushya");
		DBCollection userCollection = dbs.getCollection("Users");
		DBCursor userCur = userCollection.find();
		JSONArray allUsersInArea = new JSONArray();
		while(userCur.hasNext()) {
			DBObject user = userCur.next();
//			if(user.get("pin").equals(pincode) && user.get("expert_in").toString().equalsIgnoreCase(producttype))
				allUsersInArea.put(user);
		}
		userCur = userCollection.find();
		while(userCur.hasNext()) {
			System.out.println(userCur.next());
		}
		if(!allUsersInArea.isEmpty()) {
			return allUsersInArea.toString();
		}
		System.out.println(allUsersInArea.toString());
		return null;
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public void delCache() {
		
	}
	
	
	

}
