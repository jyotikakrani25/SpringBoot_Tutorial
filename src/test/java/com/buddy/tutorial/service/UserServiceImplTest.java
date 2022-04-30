package com.buddy.tutorial.service;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.model.UserStatus;
import com.buddy.tutorial.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserServiceImplementation")
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    @DisplayName("UserSuccess")
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
        assertEquals(user, userService.createUser(user));
    }

    @Test()
    void createUser_ExistingEmail_ExceptionThrown() {
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

    @Test
    public void getUserByID_Success() {
        //Given
        User user = new User();
        user.setId(101);
        user.setName("SSS");
        user.setEmail("jo@gmail.com");
        user.setStatus(UserStatus.ACTIVE);

        //when
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User response = userService.getUserById(user.getId());

        assertNotNull(response);
        assertEquals("SSS", response.getName());

        assertTrue(response::isActive, this::getIDMismatchMessage);
        assertFalse(response.isDisabled(), this::getIDMismatchMessage);

    }

    public String getIDMismatchMessage() {
        return "Id value is not matching";
    }

    @Test
    public void getUserById_NoUserFound_ExceptionThrown() {
        assertThrows(RuntimeException.class, () -> userService.getUserById(555));

    }

    @Test
    public void getAllUsers_WithStatus_Success() {

        //String query = "Buddy";
        List<User> users = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> expectedUsers = new PageImpl<>(users, pageable, 50);

        Mockito.when(userRepository.findByStatus(UserStatus.ACTIVE, pageable)).thenReturn(expectedUsers);

        Page<User> actualUsers = userService.getAllUsers(pageable, null, UserStatus.ACTIVE);

        assertNotNull(actualUsers);
        Mockito.verify(userRepository, Mockito.times(1)).findByStatus(any(), any());
        Mockito.verify(userRepository, Mockito.times(0)).findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(any(), any(), any());
        Mockito.verify(userRepository, Mockito.times(0)).findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(any(), any(), any(), any());
        Mockito.verify(userRepository, Mockito.times(0)).findAll(any(Pageable.class));
    }

    @Test
    public void getAllUsers_WithNameOrEmail_Success() {

        String name = "Buddy";

        List<User> users = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> expectedUsers = new PageImpl<>(users, pageable, 50);

        Mockito.when(userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(name, name, pageable)).thenReturn(expectedUsers);

        Page<User> actualUsers = userService.getAllUsers(pageable, name, null);

        assertNotNull(actualUsers);
        Mockito.verify(userRepository, Mockito.times(0)).findByStatus(any(), any());
        Mockito.verify(userRepository, Mockito.times(1)).findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(any(), any(), any());
        Mockito.verify(userRepository, Mockito.times(0)).findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(any(), any(), any(), any());
        Mockito.verify(userRepository, Mockito.times(0)).findAll(any(Pageable.class));
    }

    @Test
    public void getAllUsers_WithNameOrEmailAndStatus_Success() {

        String name = "Buddy";

        List<User> users = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> expectedUsers = new PageImpl<>(users, pageable, 50);

        Mockito.when(userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(name, name, UserStatus.ACTIVE, pageable)).thenReturn(expectedUsers);

        Page<User> actualUsers = userService.getAllUsers(pageable, name, UserStatus.ACTIVE);

        assertNotNull(actualUsers);
        Mockito.verify(userRepository, Mockito.times(0)).findByStatus(any(), any());
        Mockito.verify(userRepository, Mockito.times(0)).findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(any(), any(), any());
        Mockito.verify(userRepository, Mockito.times(1)).findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(any(), any(), any(), any());
        Mockito.verify(userRepository, Mockito.times(0)).findAll(any(Pageable.class));
    }

    @Test
    public void getAllUsers_findAllUsers_Success() {

        String name = "Buddy";

        List<User> users = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> expectedUsers = new PageImpl<>(users, pageable, 50);

        Mockito.when(userRepository.findAll(pageable)).thenReturn(expectedUsers);

        Page<User> actualUsers = userService.getAllUsers(pageable, null, null);

        assertNotNull(actualUsers);
        Mockito.verify(userRepository, Mockito.times(0)).findByStatus(any(), any());
        Mockito.verify(userRepository, Mockito.times(0)).findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(any(), any(), any());
        Mockito.verify(userRepository, Mockito.times(0)).findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(any(), any(), any(), any());
        Mockito.verify(userRepository, Mockito.times(1)).findAll(any(Pageable.class));
    }
}