package com.buddy.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.repository.UserRepository;

public interface UserService {
	
	public void createUser(User user);
	
}
