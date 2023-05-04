package com.elearningweb.library.dto;

import com.elearningweb.library.model.Category;
import com.elearningweb.library.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String body;
    private User creator;
    private Date dateCreated;
    private String image;
    private Category category;

    public PostDto(String title, String body, User creator, Date dateCreated, String image, Category category) {
        this.title = title;
        this.body = body;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.image = image;
        this.category = category;
    }
}
