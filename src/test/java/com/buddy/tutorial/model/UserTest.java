package com.buddy.tutorial.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @ParameterizedTest
    @CsvSource(value = {"ACTIVE,true", "DISABLED,false", "null,false"}, nullValues = "null")
    void userIsActive(UserStatus status, boolean expected) {
        User user = new User();
        user.setStatus(status);
        assertEquals(expected, user.isActive());
    }

    @ParameterizedTest
    @CsvSource(value = {"ACTIVE,false", "DISABLED,true", "null,false"}, nullValues = "null")
    void userIsDisabled(UserStatus status, boolean expected) {
        User user = new User();
        user.setStatus(status);
        assertEquals(expected, user.isDisabled());
    }
}