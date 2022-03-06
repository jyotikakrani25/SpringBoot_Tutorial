package com.buddy.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
/**
** {@inheritDoc}
*/
    public void createUser(final User user) {
    validateEmail();
    userRepository.save(user);
    }

    private boolean validateEmail() {
        // TODO: validate email not used again
        return true;
    }
}
