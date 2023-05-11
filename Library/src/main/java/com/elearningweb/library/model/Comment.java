package com.elearningweb.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private Post post;

    public Comment(String text, Post post) {
        this.text = text;
        this.post = post;
    }

    public Comment(String text) {
        this.text = text;
    }
}
