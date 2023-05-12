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
//    @ManyToOne
    @Column(name = "post_id")
    private Long post_id;

    public Comment(String text, Long post_id) {
        this.text = text;
        this.post_id = post_id;
    }

    public Comment(String text) {
        this.text = text;
    }
}
