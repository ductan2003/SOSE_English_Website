package com.elearningweb.library.repository;

import com.elearningweb.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT c FROM User c WHERE c.username = ?1")
    User findByUserName(String username);

    User findByUsername(String username);

    Optional<User> findById(Long userId);
}
