package com.candidjava.spring.bean;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = "employee_details")
public class Employee extends BaseEntity{

	private EmployeePersonalDetails employeePersonalDetails;
	private List<EmployeeEducationDetails> employeeEducationDetails;
	private SkillDetails skillDetails;
	
	public Employee(){
		
	}
	
	public Employee(EmployeePersonalDetails employeePersonalDetails,
			List<EmployeeEducationDetails> employeeEducationDetails, SkillDetails skillDetails) {
		this.employeePersonalDetails = employeePersonalDetails;
		this.employeeEducationDetails = employeeEducationDetails;
		this.skillDetails = skillDetails;
	}

	
	public EmployeePersonalDetails getEmployeePersonalDetails() {
		return employeePersonalDetails;
	}
	public void setEmployeePersonalDetails(EmployeePersonalDetails employeePersonalDetails) {
		this.employeePersonalDetails = employeePersonalDetails;
	}
	public List<EmployeeEducationDetails> getEmployeeEducationDetails() {
		return employeeEducationDetails;
	}
	public void setEmployeeEducationDetails(List<EmployeeEducationDetails> employeeEducationDetails) {
		this.employeeEducationDetails = employeeEducationDetails;
	}
	public SkillDetails getSkillDetails() {
		return skillDetails;
	}
	public void setSkillDetails(SkillDetails skillDetails) {
		this.skillDetails = skillDetails;
	}
}
