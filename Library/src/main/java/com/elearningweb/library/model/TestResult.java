package com.elearningweb.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int numOfCorrectAnswers;
    private int numOfWrongAnswers;

    private Date dateTaken;
    @ManyToOne
    @JoinColumn(name="user_id",nullable= false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name="exam_id",nullable= false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Exam exam;
}
