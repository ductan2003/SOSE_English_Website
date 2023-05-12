package com.elearningweb.library.service.impl;

import com.elearningweb.library.dto.CommentDto;
import com.elearningweb.library.dto.PostDto;
import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.Category;
import com.elearningweb.library.model.Comment;
import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.CategoryRepository;
import com.elearningweb.library.repository.PostRepository;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public FileServiceImpl fileService;
    @Value("${project.image}")
    private String path;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PostDto insert(PostDto postDto, MultipartFile image) throws Exception {
        Post post = this.modelMapper.map(postDto, Post.class);
        String fileName = fileService.updateFile(path, image);
        post.setImage(fileName);
        post.setDateCreated(new Date());
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
        return posts.stream().map((post) ->
                this.modelMapper.map(post, PostDto.class)).
                collect(Collectors.toList());
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
    public Post getPost(Long id) {
        return postRepository.findAllById(id);
    }

    @Override
    public Post find(Long id) {
        return postRepository.findAllById(id);
    }

    @Override
    public void createComment(Long postId, String text){
        Post post = postRepository.findAllById(postId);
        Comment comment = new Comment(text, postId);
        if (post != null) {
            post.addComment(comment);
        }
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        this.updatePost(postDto, postId);
    }

    public List<CommentDto> getAllComments(Long postId){

        return postRepository.findById(postId).stream()
                .map(video -> commentMapper.mapToDtoList(video.getComments()))
                .findAny().orElse(Collections.emptyList());
    }

}
