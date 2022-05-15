package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.model.UserStatus;
import com.buddy.tutorial.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    //@MockBean
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void createUser_Success() throws Exception {

        //given
        String request = "{\n" +
                "  \"id\": 101,\n" +
                "  \"name\": \"HHH\",\n" +
                "  \"email\": \"HHH@gmail.com\",\n" +
                "  \"status\": \"ACTIVE\",\n" +
                "  \"active\": true,\n" +
                "  \"disabled\": true\n" +
                "}";
        //when
        ResultActions response = mockmvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request));

        //then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(101))
                .andExpect(jsonPath("$.name").value("HHH"));
    }

    @Test
    void createUser_NoName_Failure() throws Exception {

        String request = "{\n" +
                "  \"id\": 102,\n" +
                "  \"email\": \"HHH@gmail.com\",\n" +
                "  \"status\": \"ACTIVE\",\n" +
                "  \"active\": true,\n" +
                "  \"disabled\": true\n" +
                "}";

        ResultActions response = mockmvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request));

        //then
        response.andDo(print())
                .andExpect(status().isBadRequest());
        //.andExpect(jsonPath("$.status").value(400))
        //.andExpect(jsonPath("$.error").value("Bad Request"));

    }

    @Test
    void createUser_NoEmail_Failure() throws Exception {

        String request = "{\n" +
                "  \"id\": 132,\n" +
                "  \"name\": \"KKK\",\n" +
                "  \"status\": \"ACTIVE\",\n" +
                "  \"active\": true,\n" +
                "  \"disabled\": true\n" +
                "}";

        ResultActions response = mockmvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request));

        //then
        response.andDo(print())
                .andExpect(status().isBadRequest());
        //.andExpect(jsonPath("$.status").value(400))
        //.andExpect(jsonPath("$.error").value("Bad Request"));

    }

    @Test
    void createUser_InvalidEmail_Failure() throws Exception {

        String request = "{\n" +
                "  \"id\": 132,\n" +
                "  \"name\": \"KKK\",\n" +
                "  \"email\": \"jyoti@.gmail\",\n" +
                "  \"status\": \"ACTIVE\",\n" +
                "  \"active\": true,\n" +
                "  \"disabled\": true\n" +
                "}";

        ResultActions response = mockmvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request));

        //then
        response.andDo(print())
                .andExpect(status().isBadRequest());
        //.andExpect(jsonPath("$.status").value(400))
        //.andExpect(jsonPath("$.error").value("Bad Request"));

    }

    @Test
    void createUser_ExistingEmail_Failure() throws Exception {

        User user = new User();
        user.setId(131);
        user.setName("KKK");
        user.setEmail("jyoti@gmail.com");
        user.setStatus(UserStatus.ACTIVE);
        repository.save(user);
        String request = "{\n" +
                "  \"id\": 133,\n" +
                "  \"name\": \"KKK\",\n" +
                "  \"email\": \"jyoti@gmail.com\",\n" +
                "  \"status\": \"ACTIVE\",\n" +
                "  \"active\": true,\n" +
                "  \"disabled\": true\n" +
                "}";
        ResultActions response = mockmvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request));

        //then
        response.andDo(print())
                .andExpect(status().is5xxServerError());
        //.andExpect(jsonPath("$.email").value("jyoti@gmail.com"));
        //.andExpect(jsonPath("$.error").value("Bad Request"));

    }

    @Test
    void getUserById_Success() throws Exception {

        String userId = "101";
        ResultActions response = mockmvc.perform(get("/users/{userId}", 101))
                .andDo(print()).andExpect(status().isNotFound());

        /*String request = "{\n" +
                "  \"page\": 0,\n" +
                "  \"size\": 1\n" +
                "}";*/
        //then
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllUsers_Success() throws Exception {

        String userId = "101";
        String request = "{\n" +
                "  \"page\": 0,\n" +
                "  \"size\": 1\n" +
                "}";

        ResultActions response = mockmvc.perform(get("/users")
                .content(mapper.writeValueAsString(request)));

        //then
        response.andDo(print())
                .andExpect(status().isOk());
    }

}