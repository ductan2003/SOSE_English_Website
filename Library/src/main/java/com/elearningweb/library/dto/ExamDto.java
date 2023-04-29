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
    private String fileQuestion;
    private String fileAnswer;

    private CategoryDto category;
    private List<QuestionDto> questionsList = new ArrayList<>();
}
