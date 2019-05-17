package com.example.mongoConnect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongoConnect.models.techDetails;
import com.example.mongoConnect.models.tickets;
import com.example.mongoConnect.repositories.ticketsRepository;
import com.example.mongoConnect.services.AssignService;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@RestController
@RequestMapping("/tickets")
public class ticketsController {
	@Autowired
	private ticketsRepository repository;
	String ticketNumber;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<tickets> getAllTickets(){
		return repository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<tickets> getTicketById(@PathVariable("id") String id) {
		return repository.findById(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public tickets createTicket(@Valid @RequestBody tickets tick) {
		DateFormat dtf = new SimpleDateFormat("dd/MM/yy");
		DateFormat ticketDate = new SimpleDateFormat("dd/MM/yy, hh:mm");
		MongoClient client = new MongoClient("localhost", 27017);
		DB dbs = client.getDB("ayushya");
		DBCollection ticketsCollection = dbs.getCollection("tickets");
		Date date = new Date();
		String dateStr = dtf.format(date);
		try {
			DBCursor lastIns = ticketsCollection.find().sort(new BasicDBObject("_id", -1));
			DBObject lastInsertedTicket = lastIns.next();
			String idOfTicket = lastInsertedTicket.get("_id").toString();
			String dateOfTicket = lastInsertedTicket.get("date_of_post").toString().split(",")[0];
			String tickNum = idOfTicket.substring(idOfTicket.length()-3);
			date = new Date();
			dateStr = dtf.format(date);
			if(dateStr.equals(dateOfTicket)) {
				System.out.println(dateStr);
				System.out.println(dateOfTicket);
				int ticketsCount = Integer.parseInt(tickNum);
				ticketNumber = Long.toString(ticketsCount+1);
				if(ticketNumber.length() == 1)
					ticketNumber = "00" + ticketNumber;
				if(ticketNumber.length() == 2)
					ticketNumber = "0" + ticketNumber;
			}else {
				System.out.println(dateStr);
				System.out.println(dateOfTicket);
				ticketNumber = "000";
			}
		} catch (Exception e) {
			ticketNumber = "000";
			System.out.println(e.getMessage());
		}
		String ticketDateStr = ticketDate.format(date);
		String[] splitDate = dateStr.split("/");
		if(splitDate[0].length() == 1)
			splitDate[0] = "0"+splitDate[0];
		if(splitDate[1].length() == 1)
			splitDate[1] = "0"+splitDate[1];
		String tricketNumber = "ATAS"+splitDate[0]+splitDate[1]+splitDate[2] + ticketNumber;
		tick.set_id(tricketNumber);
		tick.setDate_of_post(ticketDateStr);
		JSONArray users = new AssignService().getAllTechInArea(tick.getPin_code().toString(),
				tick.getProduct_category().toString());
		String tech_name = new AssignService().getUserWithMinimumTickets(users);
		tick.setTech_name(tech_name);
		repository.save(tick);
		return tick;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyTicket(@PathVariable("id") String id, @Valid @RequestBody tickets tick) {
		tick.set_id(id);
		repository.save(tick);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteTicket(@PathVariable("id") String id) {
		repository.deleteById(id);
		return "DELETED";
	}
	

	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.PUT)
	public JSONObject mod(@PathVariable("id") String id, @RequestBody JSONObject tick) {
		MongoClient client = new MongoClient("localhost", 27017);
		DB dbs = client.getDB("ayushya");
		DBCollection collection = dbs.getCollection("tickets");
		BasicDBObject updateObject = new BasicDBObject();
		if(tick.get("call_type") != null) {
			System.out.println("call_type");
			updateObject.append("$set", new BasicDBObject().append("call_type", tick.get("call_type").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("name") != null) {
			System.out.println("name");
			updateObject.append("$set", new BasicDBObject().append("name", tick.get("name").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
			
		
		if(tick.get("address_1") != null) {
			System.out.println("address_1");
			updateObject.append("$set", new BasicDBObject().append("address_1", tick.get("address_1").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("address_2") != null) {
			System.out.println("address_2");
			updateObject.append("$set", new BasicDBObject().append("address_2", tick.get("address_2").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
			
		
		if(tick.get("street") != null) {
			System.out.println("street");
			updateObject.append("$set", new BasicDBObject().append("street", tick.get("street").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
			
		
		if(tick.get("city") != null) {
			System.out.println("city");
			updateObject.append("$set", new BasicDBObject().append("city", tick.get("city").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
			
		
		if(tick.get("state") != null) {
			System.out.println("state");
			updateObject.append("$set", new BasicDBObject().append("state", tick.get("state").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
			
		
		if(tick.get("pin_code") != null) {
			System.out.println("pin_code");
			updateObject.append("$set", new BasicDBObject().append("pin_code", tick.get("pin_code").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
			
		
		if(tick.get("tech_name") != null) {
			System.out.println("tech_name");
			updateObject.append("$set", new BasicDBObject().append("tech_name", tick.get("tech_name").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
			
		
		if(tick.get("mobile_number_1") != null) {
			System.out.println("mobile_number_1");
			updateObject.append("$set", new BasicDBObject().append("mobile_number_1", tick.get("mobile_number_1").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
			
		
		if(tick.get("mobile_number_2") != null) {
			System.out.println("mobile_number_2");
			updateObject.append("$set", new BasicDBObject().append("mobile_number_2", tick.get("mobile_number_2").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("email_id") != null) {
			System.out.println("email_id");
			updateObject.append("$set", new BasicDBObject().append("email_id", tick.get("email_id").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("brand") != null) {
			System.out.println("brand");
			updateObject.append("$set", new BasicDBObject().append("brand", tick.get("brand").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("product_category") != null) {
			System.out.println("product_category");
			updateObject.append("$set", new BasicDBObject().append("product_category", tick.get("product_category").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("model_name") != null) {
			System.out.println("model_name");
			updateObject.append("$set", new BasicDBObject().append("model_name", tick.get("model_name").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("serial_number") != null) {
			System.out.println("serial_number");
			updateObject.append("$set", new BasicDBObject().append("serial_number", tick.get("serial_number").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("iw") != null) {
			System.out.println("iw");
			updateObject.append("$set", new BasicDBObject().append("iw", tick.get("iw").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("visit_date") != null) {
			System.out.println("visit_date");
			updateObject.append("$set", new BasicDBObject().append("visit_date", tick.get("visit_date").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("time_of_visit") != null) {
			System.out.println("time_of_visit");
			updateObject.append("$set", new BasicDBObject().append("time_of_visit", tick.get("time_of_visit").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("remarks") != null) {
			System.out.println("remarks");
			updateObject.append("$set", new BasicDBObject().append("remarks", tick.get("remarks").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("date_of_post") != null) {
			System.out.println("date_of_post");
			updateObject.append("$set", new BasicDBObject().append("date_of_post", tick.get("date_of_post").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("dealer_name") != null) {
			System.out.println("dealer_name");
			updateObject.append("$set", new BasicDBObject().append("dealer_name", tick.get("dealer_name").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		if(tick.get("invoice_number") != null) {
			System.out.println("invoice_number");
			updateObject.append("$set", new BasicDBObject().append("invoice_number", tick.get("invoice_number").toString()));
			BasicDBObject old = new BasicDBObject().append("_id", id);
			collection.update(old, updateObject, false, false);
		}
		
		BasicDBObject old = new BasicDBObject().append("_id", id);
		collection.update(old, updateObject, false, false);
		JSONObject js = new JSONObject();
		js.put("job", "done");
		return js;
	}
}
