package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * This API will create users in database.
     * @param user is considering as a Request Body
     * @return user.
     */
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody final User user) {
        return userService.createUser(user);
    }
}
