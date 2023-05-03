package com.elearningweb.library.repository;

import com.elearningweb.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    @Query("SELECT c FROM User c WHERE c.username = ?1")
    public User findByUserName(String username);

    public User findByResetPasswordToken(String token);
}