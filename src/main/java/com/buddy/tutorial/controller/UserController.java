package com.buddy.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.service.UserService;

@RestController
public class UserController {

	@Autowired
	public UserService userService;

	@PostMapping("/users")
	public int createUser(@RequestBody User user) {
		userService.createUser(user);
		return user.getId();
	}

}
