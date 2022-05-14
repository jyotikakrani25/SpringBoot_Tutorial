package com.buddy.tutorial;

import com.buddy.tutorial.controller.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TutorialApplicationTests {


    private final UserController userController;

    @Autowired
    TutorialApplicationTests(UserController userController) {
        this.userController = userController;
    }

    @Test
    void contextLoads() throws Exception {
        Assertions.assertNotNull(userController);
    }
}
