package com.elearningweb.library.dto;

import com.elearningweb.library.model.Post;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String text;
    private Post post;

    public CommentDto(String text, Post post) {
        this.text = text;
        this.post = post;
    }
}
