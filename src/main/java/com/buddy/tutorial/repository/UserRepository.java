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

    Page<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(String name, String email, UserStatus status, Pageable pageable);

    Page<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email, Pageable pageable);

    Page<User> findByStatus(UserStatus status, Pageable pageable);

}
