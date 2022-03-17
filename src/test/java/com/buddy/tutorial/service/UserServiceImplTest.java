package com.buddy.tutorial.service;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.model.UserStatus;
import com.buddy.tutorial.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void createUser_Success() {
        //Given
        User user = new User();
        user.setId(101);
        user.setName("SSS");
        user.setEmail("SSS@gmail.com");
        user.setStatus(UserStatus.ACTIVE);


        //when
       Mockito.when(userRepository.save(user)).thenReturn(user);

        User response = userService.createUser(user);

        //then
        assertNotNull(response);
        assertEquals(101, response.getId());
        assertEquals("SSS", response.getName());
        assertEquals(UserStatus.ACTIVE, response.getStatus());
    }

    @Test()
    void createUser_ExistingEmail() {
        //Given
        User user = new User();
        user.setId(101);
        user.setName("SSS");
        user.setEmail("jo@gmail.com");
        user.setStatus(UserStatus.ACTIVE);


        //when
        Mockito.when(userRepository.existsByEmailIgnoreCase(user.getEmail())).thenReturn(true);
        assertThrows(RuntimeException.class, () -> userService.createUser(user));
    }


}