package com.elearningweb.library.service.impl;

import com.elearningweb.library.dto.CommentDto;
import com.elearningweb.library.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentMapper {

    public List<CommentDto> mapToDtoList(List<Comment> comments){
        return comments.stream()
                .map(this::mapToDto).toList();
    }

    private CommentDto mapToDto(Comment comment) {
        return CommentDto.builder()
                .text(comment.getText())
                .build();
    }
}
