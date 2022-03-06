package com.buddy.tutorial.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.service.UserService;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

    private UserService userService;

    /**
     * This API will create users in database.
     * @param user
     * @return user.
     */
    @PostMapping("/users")
    public User createUser(@RequestBody final User user) {
        userService.createUser(user);
        return user;
    }
}
