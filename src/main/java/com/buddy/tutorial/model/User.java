package com.buddy.tutorial.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "users")
@Data
public class User {

    @Id
    private Integer id;
    private String name;
    private String email;
    private String status;
}
