package com.buddy.tutorial.service;

import com.buddy.tutorial.model.User;

public interface UserService {
    /**
     * method to create user.
     *
     * @param user user object.
     * @return user object
     */
    User createUser(User user);
}
