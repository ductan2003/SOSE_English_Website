package com.elearningweb.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String questionId;
    private String section;
    private String title;
    @Column(length = 3000)
    private String question;
    @Column(length = 5000)
    private String paragraph;
    private String correctAnswer;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private Exam exam;
}