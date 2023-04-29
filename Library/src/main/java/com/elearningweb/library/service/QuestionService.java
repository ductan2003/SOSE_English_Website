package com.elearningweb.library.service;

import com.elearningweb.library.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> findAllByExam_Id(long exam_id);
    QuestionDto findByIdAndExam_Id(long id, long exam_id);
}
