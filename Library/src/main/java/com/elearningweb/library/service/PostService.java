package com.elearningweb.library.service;

import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.dto.PostDto;
import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostDto insert(PostDto postDto, String creatorName);
    PostDto updatePost(PostDto postDto, Long postId);
    boolean deletePost(Long postId);
    List<PostDto> searchByTitle (String title);
    List<Post> getAllPosts();
    List<Post> findByUser(UserDto userDto);
    Post getPost(Long id);
    PostDto getPostById (Long id);
    Post find(Long id);
}
