package com.example.mongoConnect.services;

import org.json.simple.JSONObject;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsService {
	
	public static final String ACCOUNT_SID = "AC647a756063a7d5a9538757b23492dbcb";
	public static final String AUTH_TOKEN = "d25469fe6ec00d1371558ef28b750f0b";
	
	public String sendSMS(JSONObject smsObject) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
//		Message message = Message.creator(
//				new PhoneNumber("whatsapp:+918050825266"),
//				new PhoneNumber("whatsapp:+918310521165"),
//				"Hello this is test message to shreyas").create();
		System.out.println(smsObject.toString());
		Message message = Message.creator(
				new PhoneNumber("+91"+smsObject.get("to").toString()),
				new PhoneNumber("+16264005615"),
				smsObject.get("body").toString()).create();
		
		System.out.println(message.getSid());
		return message.getSid().toString();
	}
	
	public String sendEmail() {
		Email from = new Email("shreyas.shivajirao@gmail.com");
		String subject = "Test email from shreyas";
		Email to = new Email("shreyas.shivajirao@gmail.com");
		Content content = new Content("text/plain", "Trying to send it for the first time");
		Mail mail = new Mail(from, subject, to, content);
		
		SendGrid sg = new SendGrid("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");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
		return null;
	}

}
