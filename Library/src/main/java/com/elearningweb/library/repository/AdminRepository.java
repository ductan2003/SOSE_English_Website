package com.elearningweb.library.repository;

import com.elearningweb.library.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
//    @Query(value = "query", nativeQuery = true)
    Admin findByUsername(String username);
}
