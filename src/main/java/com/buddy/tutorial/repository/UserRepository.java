package com.buddy.tutorial.repository;

import com.buddy.tutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * method to check email exist or not in database.
     *
     * @param email email address
     * @return If email exists then return true else false
     */
    boolean existsByEmailIgnoreCase(String email);
}
