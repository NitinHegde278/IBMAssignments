package com.training.ibm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTest {

	@Autowired
	UserDao dao;
	
	Integer getCountOfUsers() {
		return dao.getCountOfUsers();
	}
	
	String getUserName(String id, String pin) {
		return dao.getUserName(id, pin);
	}
	
	List<User> getUserDetails() {
		return dao.getUserDetails();
	}
	
	void addUser(User user) {
		dao.addUser(user);
	}
	
	void updateUser(String id, User user) {
		dao.updateUser(id, user);
	}
	
	void deleteUser(String id) {
		dao.deleteUser(id);
	}
}
