package com.buddy.tutorial.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity(name = "users")
@Data
public class User {

    @Id
    private Integer id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    private UserStatus status;

    public boolean isActive() {
        return status != null && status == UserStatus.ACTIVE;
    }

    public boolean isDisabled() {
        return status != null && status == UserStatus.DISABLED;
    }
}
