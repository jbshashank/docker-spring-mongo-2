package com.candidjava.spring.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EmployeePersonalDetails {

	private String empFirstName;
	private String empMiddleName;
	private String empLastName;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date DOB;
	private String POB;
	private String aboutMe;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date DOJ;
	private String gender;
	private String expertiesLevel;
	private String role;
	private String salary;
	private String location;
	private String skills;
	private String address;
	private String age;
	private String city;
	private String state;
	private String pincode;
	private String uploadDir;

	public EmployeePersonalDetails(){

	}

	public EmployeePersonalDetails(String empFirstName, String empMiddleName, String empLastName, Date DOB, String POB, String aboutMe, Date DOJ, String gender, String expertiesLevel, String role, String salary, String location, String skills, String address, String age, String city, String state, String pincode, String uploadDir) {
		this.empFirstName = empFirstName;
		this.empMiddleName = empMiddleName;
		this.empLastName = empLastName;
		this.DOB = DOB;
		this.POB = POB;
		this.aboutMe = aboutMe;
		this.DOJ = DOJ;
		this.gender = gender;
		this.expertiesLevel = expertiesLevel;
		this.role = role;
		this.salary = salary;
		this.location = location;
		this.skills = skills;
		this.address = address;
		this.age = age;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.uploadDir = uploadDir;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public String getEmpMiddleName() {
		return empMiddleName;
	}
	public void setEmpMiddleName(String empMiddleName) {
		this.empMiddleName = empMiddleName;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getPOB() {
		return POB;
	}
	public void setPOB(String pOB) {
		POB = pOB;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Date getDOJ() {
		return DOJ;
	}

	public void setDOJ(Date DOJ) {
		this.DOJ = DOJ;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getExpertiesLevel() {
		return expertiesLevel;
	}

	public void setExpertiesLevel(String expertiesLevel) {
		this.expertiesLevel = expertiesLevel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}
