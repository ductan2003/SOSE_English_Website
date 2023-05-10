package com.elearningweb.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private long id;

    private String section;
    private long questionId;
    private String question;
    private String paragraph;
    private String correctAnswer;
}
