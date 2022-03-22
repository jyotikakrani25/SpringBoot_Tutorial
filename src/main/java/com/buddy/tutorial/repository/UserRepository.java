package com.buddy.tutorial.repository;

import com.buddy.tutorial.model.User;
import com.buddy.tutorial.model.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * method to check email exist or not in database.
     *
     * @param email email address
     * @return If email exists then return true else false
     */
    boolean existsByEmailIgnoreCase(String email);

    Page<User> findByNameAndStatus(String name, UserStatus status, Pageable pageable);

    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<User> findByStatus(UserStatus status, Pageable pageable);

}
