package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.model.UserStatus;
import com.buddy.tutorial.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    /**
     * This API will create users in database.
     *
     * @param user is considering as a Request Body
     * @return user.
     */
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody final User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users/{userId}")
    public User getUserByID(@PathVariable final Integer userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/users")
    public Page<User> getAllUsers(final Pageable usersPageable, @RequestParam(required = false) final String query, @RequestParam(required = false) final UserStatus status) {

        return userService.getAllUsers(usersPageable, query, status);
    }
}
