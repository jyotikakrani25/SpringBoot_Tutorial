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
     * * {@inheritDoc}
     *
     * @return
     */
    public User createUser(final User user) {
        validateEmail(user);
        return userRepository.save(user);
    }

    private boolean validateEmail(User user) {

        if (user.getEmail() == null) throw new RuntimeException("Email can't be null");
        // TODO: validate email not used again
        return true;
    }
}
