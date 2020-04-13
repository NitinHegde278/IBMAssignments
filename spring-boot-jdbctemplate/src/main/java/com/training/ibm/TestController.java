package com.training.ibm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	ServiceTest service;
	
	@RequestMapping("/users")
	Integer getCountOfUsers() {
		return service.getCountOfUsers();
	}
	
	@RequestMapping("/users/name/{id}/{pin}")
	String getUserName(@PathVariable String id,@PathVariable String pin) {
		return service.getUserName(id, pin);
	}
	
	@RequestMapping("/users/details")
	List<User> getUserDetails() {
		return service.getUserDetails();
	}
	
	@RequestMapping(method = RequestMethod.POST, value =  "/users/add")
	void addUser(@RequestBody User user) {
		service.addUser(user);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
	void updateUser(@PathVariable String id, @RequestBody User user) {
		service.updateUser(id, user);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/delete/{id}")
	void deleteUser(@PathVariable String id) {
		service.deleteUser(id);
	}
	
	
}
