package com.elearningweb.admin.controller.user;

import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.service.impl.ExamServiceImpl;
import com.elearningweb.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ExamController {
    @Autowired
    ExamServiceImpl examService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/exams/all")
    public List<ExamDto> listExams() {
        return examService.findAllExams();
    }
    @GetMapping("/exams/id={id}")
    public ExamDto getExamById(@PathVariable("id") long id) {
        return examService.findById(id);
    }
    @GetMapping("/exams/{category}")
    public List<ExamDto> listExamsByCategory(@PathVariable("category") String category) {
        return examService.findAllExamsByCategory(category);
    }
    @GetMapping("/exams/year={year}")
    public ExamDto getExamByYear(@PathVariable("year") String year) {
        return examService.findByYear(year);
    }
    @GetMapping("/exams/year={year}/{category}")
    public ExamDto getExamByYear(@PathVariable("year") String year,
                                 @PathVariable("category") String category) {
        return examService.findByYearAndCategory(year, category);
    }

    @GetMapping("exams/{category}/id={id}")
    public List<ExamDto> listExamsByCategoryAndId(@PathVariable("category") String category,
                                                  @PathVariable("id") long id) {
        return examService.findAllExamsByCategoryAndId(category, id);
    }

    @GetMapping("exams/year={year}/{category}/id={id}")
    public ExamDto getExamByYearAndCategoryAndId(@PathVariable("category") String category,
                                                 @PathVariable("id") long id,
                                                 @PathVariable("year") String year) {
        return examService.findByYearAndCategoryAndId(year, category, id);
    }
}