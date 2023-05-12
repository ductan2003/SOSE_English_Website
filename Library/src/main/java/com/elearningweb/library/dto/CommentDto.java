package com.elearningweb.library.dto;

import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String text;
    private Long post_id;
    private String creatorName;
    private String creatorImage;


    public CommentDto(String text, Long post_id) {
        this.text = text;
        this.post_id = post_id;
    }

    public CommentDto(String text, Long post_id, String creatorName) {
        this.text = text;
        this.post_id = post_id;
        this.creatorName = creatorName;
    }
}
