package com.elearningweb.library.service.impl;

import com.elearningweb.library.model.Comment;
import com.elearningweb.library.repository.CommentRepository;
import com.elearningweb.library.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Optional<Comment> getComments(Long postId) {
        return commentRepository.findById(postId);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public boolean deleteComment(Long id) {
        commentRepository.deleteById(id);
        return true;
    }
}
