package com.elearningweb.library.service.impl;

import com.elearningweb.library.dto.PostDto;
import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.PostRepository;
import com.elearningweb.library.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void insert(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> findByUser(User user) {
        return postRepository.findByCreator(user.getUsername());
    }

    @Override
    public boolean deletePost(Long postId) {
        Post thePost = postRepository.findAllById(postId);
        if(thePost == null)
            return false;
        postRepository.deleteById(postId);
        return true;
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findAllById(id);
    }

    @Override
    public Post find(Long id) {
        return postRepository.findAllById(id);
    }
}
