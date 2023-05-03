package com.elearningweb.library.service;

import com.elearningweb.library.dto.ExamDto;

import java.io.IOException;
import java.util.List;

public interface ExamService {
    ExamDto save(ExamDto examDto) throws IOException;
    void delete(long ids);
    List<ExamDto> findAllExams();
    List<ExamDto> findAllExamsByCategory(String category);
    List<ExamDto> findAllExamsByCategoryAndId(String category, long id);
    ExamDto findById(long id);

}
