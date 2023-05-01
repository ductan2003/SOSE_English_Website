package com.elearningweb.library.service;

import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    void insert(Post post);
    List<Post> findByUser(User user);
    boolean deletePost(Long postId);
    Optional<Post> getPost(Long id);
    Optional<Post> find(Long id);
}
