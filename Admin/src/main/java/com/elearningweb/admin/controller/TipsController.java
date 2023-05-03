package com.elearningweb.admin.controller;

import com.elearningweb.admin.config.CustomUserDetails;
import com.elearningweb.library.converter.Converter;
import com.elearningweb.library.model.Comment;
import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;
import com.elearningweb.library.service.PostService;
import com.elearningweb.library.service.impl.CommentServiceImpl;
import com.elearningweb.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("http://localhost:8080/") //Cái này của VueJS

@RequestMapping(
        method = {RequestMethod.POST, RequestMethod.GET}
)
public class TipsController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    Converter converter;

    @GetMapping("/tips")
    public List<Post> posts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping("/post")
    public String publishPost(@RequestBody Post post) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(post.getDateCreated() == null) {
            post.setDateCreated(new Date());
        }
        post.setCreator(converter.userToEntity(userService.getUser(userDetails.getUsername())));
        postService.insert(post);
        return "Post was publshed!!!";
    }

    @GetMapping("/posts/{username}")
    public List<Post> postByUser(@PathVariable String username) {
        return postService.findByUser(converter.userToEntity(userService.getUser(username)));
    }

    @DeleteMapping("/post/{id}")
    public  boolean deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @DeleteMapping("/comment/{id}")
    public boolean deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @GetMapping("/comments/{postId}")
    public Optional<Comment> getComments (@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @PostMapping("/post/postComment")
    public boolean postComment(@RequestBody Comment comment) {
        Post post = postService.find(comment.getId());
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = converter.userToEntity(userService.getUser(userDetails.getUsername()));
        if(post == null || creator == null) return false;
        commentService.saveComment(new Comment(comment.getText(), post, creator));
        return true;
    }
}
