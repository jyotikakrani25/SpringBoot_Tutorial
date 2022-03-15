package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        user.setStatus("Engineer");

        //When
        Mockito.when(userService.createUser(user)).thenReturn(user);


        User response = controller.createUser(user);

        //then
        assertNotNull(response);
        assertEquals(101, response.getId());
        assertEquals("SSS", response.getName());
        assertEquals("Engineer", response.getStatus());
    }


}