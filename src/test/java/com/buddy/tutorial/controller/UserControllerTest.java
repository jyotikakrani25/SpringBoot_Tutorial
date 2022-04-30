package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.model.UserStatus;
import com.buddy.tutorial.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @Test
    public void createUserAPITest() {
        //Given
        User user = new User();
        user.setId(101);
        user.setName("SSS");
        user.setEmail("SSS@gmail.com");
        user.setStatus(UserStatus.ACTIVE);

        //When
        Mockito.when(userService.createUser(user)).thenReturn(user);


        User response = controller.createUser(user);

        //then
        assertNotNull(response);
        assertEquals(101, response.getId());
        assertEquals("SSS", response.getName());
        assertEquals(UserStatus.ACTIVE, response.getStatus());
    }

    @Test
    public void getUserById_Success() {

        User user = new User();
        user.setId(505);

        Mockito.when(userService.getUserById(505)).thenReturn(user);

        User response = controller.getUserByID(505);

        assertNotNull(response);
        assertEquals(505, response.getId());

    }


    @Test
    public void getAllUser_Success() {

        String name = "buddy";
        List<User> users = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> expectedUsers = new PageImpl<>(users, pageable, 50);

        Mockito.when(userService.getAllUsers(pageable, name, UserStatus.ACTIVE)).thenReturn(expectedUsers);

        Page<User> response = controller.getAllUsers(pageable, name, UserStatus.ACTIVE);

        assertNotNull(response);
    }
}