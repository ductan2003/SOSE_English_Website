package com.elearningweb.library.service;

import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.dto.PostDto;
import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;

import java.io.IOException;
import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    void insert(Post post);
    List<Post> findByUser(User user);
    boolean deletePost(Long postId);
    Post getPost(Long id);
    Post find(Long id);

}
