package com.buddy.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buddy.tutorial.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
