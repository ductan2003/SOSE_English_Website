package com.elearningweb.library.repository;

import com.elearningweb.library.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    //    @Query(value = "query", nativeQuery = true)
    Admin findByUsername(String username);
}