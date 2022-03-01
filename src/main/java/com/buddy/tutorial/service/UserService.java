package com.buddy.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void createUser(User user) {
		userRepository.save(user);
	}
	

}
