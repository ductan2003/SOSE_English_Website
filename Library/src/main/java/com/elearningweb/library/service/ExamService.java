package com.elearningweb.library.service;

import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.model.Exam;

import java.io.IOException;
import java.util.List;

public interface ExamService {
    ExamDto save(ExamDto examDto) throws IOException;
    void delete(long id);
    List<ExamDto> findAllExams();
    List<ExamDto> findAllExamsByCategory(String category);
    ExamDto findByCategoryAndId(String category, long id);
    ExamDto findById(long id);
    List<ExamDto> findAllByYear(String year);
    List<ExamDto> findAllByYearAndCategory(String year, String category);
    ExamDto findByYearAndCategoryAndId(String year, String category, long id);

}
