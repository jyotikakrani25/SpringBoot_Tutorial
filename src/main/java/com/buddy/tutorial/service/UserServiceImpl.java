package com.buddy.tutorial.service;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private void validateEmail(final User user) {

        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new RuntimeException("Email already exist");
        }
    }
}
