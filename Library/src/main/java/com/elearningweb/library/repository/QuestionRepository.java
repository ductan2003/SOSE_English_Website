package com.elearningweb.library.repository;

import com.elearningweb.library.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByExam_Id(long exam_id);
    Question findByIdAndExam_Id(long id, long exam_id);
}
