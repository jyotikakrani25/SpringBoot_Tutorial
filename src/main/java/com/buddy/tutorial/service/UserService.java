package com.buddy.tutorial.service;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.model.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    /**
     * method to create user.
     *
     * @param user user object.
     * @return user object
     */
    User createUser(User user);

    User getUserById(Integer userId);

    Page<User> getAllUsers(Pageable pageable, String query, UserStatus status);
}
