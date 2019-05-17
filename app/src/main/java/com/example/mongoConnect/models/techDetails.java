package com.example.mongoConnect.models;

public class techDetails {
	
	String _id;
	String name;
	String expert_in;
	String pin;
	int no_assigned;
	
	
	public techDetails(String _id, String name, String expert_in, String pin, int no_assigned) {
		super();
		this._id = _id;
		this.name = name;
		this.expert_in = expert_in;
		this.pin = pin;
		this.no_assigned = no_assigned;
	}


	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getExpert_in() {
		return expert_in;
	}


	public void setExpert_in(String expert_in) {
		this.expert_in = expert_in;
	}


	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}


	public int getNo_assigned() {
		return no_assigned;
	}


	public void setNo_assigned(int no_assigned) {
		this.no_assigned = no_assigned;
	}
	
	
	
	

}
