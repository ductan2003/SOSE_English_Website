package com.elearningweb.library.dto;

import com.elearningweb.library.model.Category;
import com.elearningweb.library.model.User;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 1000, message = "Invalid description!(3-1000 characters)")
    private String description;

    public PostDto(String title, String body, User creator, Date dateCreated, String image, Category category) {
        this.title = title;
        this.body = body;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.image = image;
        this.category = category;
    }

    public PostDto(String title, String body, User creator, String image, Category category, String description) {
        this.title = title;
        this.body = body;
        this.creator = creator;
        this.image = image;
        this.category = category;
        this.description = description;
    }
}
