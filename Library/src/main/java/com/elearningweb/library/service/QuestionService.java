package com.elearningweb.library.service;

import com.elearningweb.library.dto.QuestionDto;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    List<QuestionDto> findAllByExam_Id(long exam_id);

    QuestionDto findByIdAndExam_Id(long id, long exam_id);

    QuestionDto findById(long id);

    Map<Object, Object> evalQuiz(List<String> answers, long examId);
}
