package com.elearningweb.library.dto;

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

}
