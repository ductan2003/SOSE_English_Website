package com.elearningweb.library.service.impl;

import com.elearningweb.library.dto.PostDto;
import com.elearningweb.library.model.Category;
import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.CategoryRepository;
import com.elearningweb.library.repository.PostRepository;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public UserRepository userRepository;

    @Override
    public PostDto insert(PostDto postDto, String categoryName, String creatorName) {
        Category category = categoryRepository.findByName(categoryName);
        User user = userRepository.findByUsername(creatorName);
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImage("default.png");
        post.setDateCreated(new Date());
        post.setCategory(category);
        post.setCreator(user);
        Post newPost = postRepository.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepository.findAllById(postId);
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setImage(postDto.getImage());
        Post updatePost = postRepository.save(post);
        return this.modelMapper.map(updatePost, PostDto.class);
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
    public List<PostDto> searchByTitle (String title){
        List<Post> posts = postRepository.searchByTitle("%" + title + "%");
        List<PostDto> postDtos = posts.stream().map((post) ->
                this.modelMapper.map(post, PostDto.class)).
                collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public PostDto getPostById (Long id) {
        Post post = postRepository.findAllById(id);
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<Post> findByUser(User user) {
        return postRepository.findByCreator(user.getUsername());
    }
    @Override
    public List<PostDto> getPostByCategory(String categoryName){
        Category category = categoryRepository.findByName(categoryName);
        List<Post> posts = postRepository.findAllByCategory(category);
        List<PostDto> postDtos = posts.stream().map((post) ->
            this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
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
