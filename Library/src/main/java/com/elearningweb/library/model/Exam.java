package com.elearningweb.library.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Year;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @Nullable
    private String fileQuestion;
    @Nullable
    private String fileAnswer;

    private String description;
    private int year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "exam", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Question> questionsList;
}
