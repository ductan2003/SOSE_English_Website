package com.elearningweb.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    @ManyToOne
    private User creator;
    private Date dateCreated;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Category category;
    private String description;

    public Post(String title, String body, User creator, Date dateCreated, String image, Category category) {
        this.title = title;
        this.body = body;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.image = image;
        this.category = category;
    }
}
