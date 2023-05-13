package com.elearningweb.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    private long id;

    private String title;

    private String description;
    private String year;
    private String fileQuestion;
    private String fileImage;

    private CategoryDto category;
    private List<QuestionDto> questionsList = new ArrayList<>();

    public ExamDto(String title, String description, String year, String fileQuestion, String fileImage, CategoryDto category) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.fileQuestion = fileQuestion;
        this.fileImage = fileImage;
        this.category = category;
    }
}
