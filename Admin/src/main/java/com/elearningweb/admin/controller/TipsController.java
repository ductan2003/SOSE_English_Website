package com.elearningweb.admin.controller;

import com.elearningweb.admin.config.CustomUserDetails;
import com.elearningweb.library.dto.PostDto;
import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.*;
import com.elearningweb.library.service.FileService;
import com.elearningweb.library.service.PostService;
import com.elearningweb.library.service.impl.CommentServiceImpl;
import com.elearningweb.library.service.impl.UserServiceImpl;
import com.elearningweb.library.util.StreamUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/tips")
public class TipsController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private FileService fileService;
//    @Value("${project.image}")
    private String path;
    @Autowired
    public ModelMapper modelMapper;

    //Post tips
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

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable String categoryName) {
        List<PostDto> postDtos = this.postService.getPostByCategory(categoryName);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @PostMapping("/{categoryName}/{creatorName}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PostDto> publishPost(@RequestBody PostDto postDto,
                            @PathVariable String categoryName,
                            @PathVariable String creatorName) throws Exception {
        PostDto publishPost = postService.insert(postDto, categoryName, creatorName);
        return new ResponseEntity<PostDto>(publishPost, HttpStatus.OK );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Long id) throws Exception{
        PostDto updatePost = postService.updatePost(postDto, id);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("title") String title) {
        List<PostDto> postDtos = postService.searchByTitle(title);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    //Comment
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
        UserDto creatorDto = userService.getUser(userDetails.getUsername());
        User creator = this.modelMapper.map(creatorDto, User.class);
        if(post == null || creator == null) return false;
        commentService.saveComment(new Comment(comment.getText(), post, creator));
        return true;
    }

    //Image upload
    @PostMapping("/file/upload/{id}")
    public ResponseEntity<PostDto> uploadFile(@RequestParam("file")MultipartFile file,
                                              @PathVariable Long id) throws Exception {
        PostDto postDto = postService.getPostById(id);
        String fileName = fileService.updateFile(path, file);
        postDto.setImage(fileName);
        PostDto updatePost = postService.updatePost(postDto, id);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    @GetMapping(value = "/file/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadFile(@PathVariable("fileName") String fileName,
                             HttpServletResponse response) throws Exception {
        InputStream resource = this.fileService.getResource(path, fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
