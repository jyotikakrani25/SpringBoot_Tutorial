package com.buddy.tutorial.service;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.model.UserStatus;
import com.buddy.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Override
    public User createUser(final User user) {
        validateEmail(user);
        return userRepository.save(user);
    }

    private void validateEmail(final User user) {
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new RuntimeException("Email already exist");
        }
    }

    @Override
    public User getUserById(final Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public Page<User> getAllUsers(final Pageable pageable, final String query, final UserStatus status) {
        if (query != null && status != null) {
            return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(query, query, status, pageable);
        } else if (query != null) { //select U* from users where name = %s and email = %s : select * from Users where name = buddy ,or email =
            return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query, pageable); // either name or email
        } else if (status != null) {
            return userRepository.findByStatus(status, pageable);
        } else {
            return userRepository.findAll(pageable);
        }
    }


}
