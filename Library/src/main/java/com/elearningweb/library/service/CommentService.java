package com.elearningweb.library.service;

import com.elearningweb.library.model.Comment;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> getComments(Long postId);
    void saveComment(Comment comment);
    boolean deleteComment(Long id);

}
