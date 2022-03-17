package com.buddy.tutorial.repository;

import com.buddy.tutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmailIgnoreCase(String email);
}
