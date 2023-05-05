package com.elearningweb.library.repository;

import com.elearningweb.library.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findAllByCategory_Name(String category_name);
    List<Exam> findAllByCategory_NameAndId(String category_name, long id);
    Exam findById(long id);
    Exam findByYear(String year);
    Exam findByYearAndCategory(String  year, String category);
    Exam findByYearAndCategoryAndId(String year, String category, long id);
    void deleteById(long id);
}
