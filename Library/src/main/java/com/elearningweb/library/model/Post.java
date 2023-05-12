package com.elearningweb.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String body;

    private Date dateCreated;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", referencedColumnName = "post_id")
    List<Comment> comments = new ArrayList<>();

    public Post(String title, String body, Date dateCreated, String image) {
        this.title = title;
        this.body = body;
        this.dateCreated = dateCreated;
        this.image = image;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }
}
