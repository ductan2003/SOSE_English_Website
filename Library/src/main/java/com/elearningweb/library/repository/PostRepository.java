package com.elearningweb.library.repository;

import com.elearningweb.library.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreator(String username);
    Post findAllById(Long id);
}
