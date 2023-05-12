package com.elearningweb.admin.controller;

import com.elearningweb.admin.config.CustomUserDetails;
import com.elearningweb.library.dto.*;
import com.elearningweb.library.model.*;
import com.elearningweb.library.service.FileService;
import com.elearningweb.library.service.PostService;
import com.elearningweb.library.service.impl.CommentServiceImpl;
import com.elearningweb.library.service.impl.FileServiceImpl;
import com.elearningweb.library.service.impl.UserServiceImpl;
import com.elearningweb.library.util.ConstraintViolationExceptionHandler;
import com.elearningweb.library.util.FileUploadUtils;
import com.elearningweb.library.util.StreamUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.lang.ClassCastException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    @Value("${project.image}")
    private String path;
    @Autowired
    public ModelMapper modelMapper;

    //Post tips
    @GetMapping("/post/all")
    public ResponseEntity<?> posts() {
        List<Post> list = postService.getAllPosts();

        Map<Object, Object> map = Map.of("total", list.size(), "posts", list);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/post/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPost(postId);
    }


    @PostMapping("/post/publishTips")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PostDto> publishPost(@RequestPart String title,
                                               @RequestPart String body,
//                                               @RequestPart byte[] body,
                                               @RequestPart MultipartFile image,
                                               @RequestPart String description) throws Exception {
        PostDto postDto = new PostDto(title, body, description);
        PostDto publishPost = postService.insert(postDto, image);
        return new ResponseEntity<PostDto>(publishPost, HttpStatus.OK );
    }

    @PutMapping("/post/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestPart String title,
                                              @RequestPart String body,
                                              @RequestPart MultipartFile image,
                                              @RequestPart String description,
                                              @PathVariable Long postId) throws Exception{
        PostDto postDto = new PostDto(postId, title, body, description);
        String fileName = fileService.updateFile(path, image);
        postDto.setImage(fileName);
        PostDto updatePost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/post/delete/{postId}")
    public boolean deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }

    @GetMapping("/post/search/{title}")
    public ResponseEntity<?> searchByTitle(@PathVariable("title") String title) {
        List<PostDto> list = postService.searchByTitle(title);
        Map<Object, Object> map = Map.of("total", list.size(), "posts", list);
        return ResponseEntity.ok(map);
    }

    //Comment
    @DeleteMapping("/comments/{commentId}")
    public boolean deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @GetMapping("/comments/{postId}")
    public ResponseEntity<List<CommentDto>> getComments (@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getAllComments(postId));
    }


    @PostMapping(value ="/comments/postComment")
    @ResponseStatus(HttpStatus.CREATED)
//    Test PostMan thì set content-type = application/json
    public ResponseEntity<CommentDto> postComment(@RequestPart String text, @RequestPart String postId, @RequestPart String creatorName) {
        CommentDto commentDto = postService.createComment((long) Integer.parseInt(postId), text, creatorName);
        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
    }
//    @GetMapping("/getUser")
//    public ResponseEntity<UserDto> getCurrentUser(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken))
//        {
//            if(auth.getDetails() !=null)
//                System.out.println(auth.getDetails().getClass());
//            if( auth.getDetails() instanceof UserDetails)
//            {
//                System.out.println("UserDetails");
//            }
//            else
//            {
//                System.out.println("!UserDetails");
//            }
//        }
//        UserDto userDto = (UserDto) auth.getDetails();
//        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
//    }

    //Image upload
    @PostMapping("/file/upload/{id}")
    public ResponseEntity<PostDto> uploadFile(@RequestParam("file")MultipartFile file,
                                              @PathVariable Long id) throws Exception {
        PostDto postDto = postService.getPostById(id);
        String fileName = fileService.updateFile(path, file);
        postDto.setImage(fileName);
        PostDto updatePost = postService.updatePost(postDto, id);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }


    @GetMapping(value = "/file/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadFile(@PathVariable("fileName") String fileName,
                             HttpServletResponse response) throws Exception {
        InputStream resource = this.fileService.getResource(path, fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}

