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
    List<ExamDto> findAllExamsByCategoryAndId(String category, long id);
    ExamDto findById(long id);
    ExamDto findByYear(int year);
    ExamDto findByYearAndCategory(int year, String category);
    ExamDto findByYearAndCategoryAndId(int year, String category, long id);

}
