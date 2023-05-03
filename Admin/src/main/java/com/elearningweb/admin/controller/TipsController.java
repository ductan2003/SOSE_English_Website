package com.elearningweb.admin.controller;

import com.elearningweb.admin.config.CustomUserDetails;
import com.elearningweb.library.dto.PostDto;
import com.elearningweb.library.model.*;
import com.elearningweb.library.service.PostService;
import com.elearningweb.library.service.impl.CommentServiceImpl;
import com.elearningweb.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin("http://localhost:8080/") //Cái này của VueJS

//@RequestMapping(
//        method = {RequestMethod.POST, RequestMethod.GET}
//)
@RequestMapping("/tips")
public class TipsController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/all")
    public List<Post> posts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/{username}")
    public List<Post> getPostByUser(@PathVariable String username) {
        return postService.findByUser(userService.getUser(username));
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void publishPost(@RequestPart String title,
                               @RequestPart String body,
                               @RequestPart Admin creator,
                               @RequestPart Date dateCreated,
                               @RequestPart String image,
                               @RequestPart Category category) throws Exception {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dateCreated = new Date();
        creator = userService.getUser(userDetails.getUsername());
        Post post = new Post(title, body, creator, dateCreated, image, category);
        postService.insert(post);
    }

    @DeleteMapping("/{id}")
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

    @PostMapping("/postComment")
    public boolean postComment(@RequestBody Comment comment) {
        Post post = postService.find(comment.getId());
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = userService.getUser(userDetails.getUsername());
        if(post == null || creator == null) return false;
        commentService.saveComment(new Comment(comment.getText(), post, creator));
        return true;
    }
}
