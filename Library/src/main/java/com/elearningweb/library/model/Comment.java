package com.elearningweb.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Column(name = "post_id")
    private Long post_id;
    private String creatorName;
    private String creatorImage;

    public Comment(String text, Long post_id, String creatorName, String creatorImage) {
        this.text = text;
        this.post_id = post_id;
        this.creatorName = creatorName;
        this.creatorImage = creatorImage;
    }
}
