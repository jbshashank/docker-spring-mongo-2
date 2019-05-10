package com.candidjava.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.candidjava.spring.bean.Cities;
import com.candidjava.spring.bean.States;
import com.candidjava.spring.repository.CityRepository;
import com.candidjava.spring.repository.StateRepository;
import com.candidjava.spring.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.Employee;
import com.candidjava.spring.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import com.candidjava.spring.helper.GlobalConstants;

import static com.candidjava.spring.helper.GlobalConstants.DIR_PATH_SEPERATOR;
import static com.candidjava.spring.helper.GlobalConstants.IMAGE_UPLOAD_FOLDER;
import static com.candidjava.spring.helper.GlobalConstants.UPLOADED_FOLDER_PATH;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void createUser(Employee user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public List<Employee> getUser() {
		// TODO Auto-generated method stub
		return (List<Employee>) userRepository.findAll();
	}

	@Override
	public Employee findById(String id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public Employee update(Employee user, String l) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(String id) {
		// TODO Auto-generated method stub
		userRepository.delete(id);
	}

	@Override
	public Employee updatePartially(Employee emp, String id) {
		// TODO Auto-generated method stub
		Employee emp_Det = findById(id);
		return userRepository.save(emp);
	}

	public List<Employee> findByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByEmpFirstName(name);


	}

	public List<Employee> findByNameStartingWith(String regexp) {
		// TODO Auto-generated method stub
		return (List<Employee>) userRepository.findAll();
	}

	public List<Employee> findByNameEndingWith(String regexp) {
		// TODO Auto-generated method stub
		return (List<Employee>) userRepository.findAll();
	}

	public List<Employee> findByAgeBetween(int ageGT, int ageLT){
		// TODO Auto-generated method stub
		return (List<Employee>) userRepository.findAll();
	}

	public String uploadImage(MultipartFile uploadfile) {

		try {
			String path = UPLOADED_FOLDER_PATH + IMAGE_UPLOAD_FOLDER + DIR_PATH_SEPERATOR
					+ "employees" + DIR_PATH_SEPERATOR;
			/*Runtime.getRuntime().exec("chmod 777 -R " + UPLOADED_FOLDER_PATH); if linux system*/
			File folder = new File(path);
			if (!folder.exists()) {
				if (folder.mkdirs()) {
					//Runtime.getRuntime().exec("chmod 644 " + path); If linux system
				}
			}
			GlobalUtils.uploadFile(folder.getAbsolutePath(), uploadfile);
			String imagePath = IMAGE_UPLOAD_FOLDER + "/employees/" + uploadfile.getOriginalFilename();

			return imagePath;
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public List<Cities> getCityByState(int id) {
		return (List<Cities>) cityRepository.findByStateId(id);
	}

	@Override
	public Cities getCityById(String id) {
		return cityRepository.findOne(id);
	}

	@Override
	public List<States> getStates() {
		return (List<States>) stateRepository.findAll();
	}

	@Override
	public List<Cities> getCities() {
		return (List<Cities>) cityRepository.findAll();
	}
}
