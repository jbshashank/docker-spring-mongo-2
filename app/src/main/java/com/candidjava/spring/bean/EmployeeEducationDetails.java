package com.candidjava.spring.bean;

public class EmployeeEducationDetails {
	private String education;
	private String yearOfCompletion;
	
	public EmployeeEducationDetails(){
		
	}
	
	public EmployeeEducationDetails(String education, String yearOfCompletion) {
		super();
		this.education = education;
		this.yearOfCompletion = yearOfCompletion;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getYearOfCompletion() {
		return yearOfCompletion;
	}
	public void setYearOfCompletion(String yearOfCompletion) {
		this.yearOfCompletion = yearOfCompletion;
	}	
}
