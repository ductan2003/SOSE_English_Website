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
    //1.Get all exams
    @GetMapping("/exams/all")
    public List<ExamDto> listExams() {
        return examService.findAllExams();
    }
    //2.Get exam by id
    @GetMapping("/exams/id={id}")
    public ExamDto getExamById(@PathVariable("id") long id) {
        return examService.findById(id);
    }
    //3.Get all by category
    @GetMapping("/exams/{category}")
    public List<ExamDto> listExamsByCategory(@PathVariable("category") String category) {
        return examService.findAllExamsByCategory(category);
    }
    //4. Get all by year
    @GetMapping("/exams/year={year}")
    public List<ExamDto> getExamByYear(@PathVariable("year") String year) {
        return examService.findAllByYear(year);
    }
    //5. Get all by year and category
    @GetMapping("/exams/year={year}/{category}")
    public List<ExamDto> getExamByYearAndCategory(@PathVariable("year") String year,
                                 @PathVariable("category") String category) {
        return examService.findAllByYearAndCategory(year, category);
    }
    //6. Get all by category and id
    @GetMapping("exams/{category}/id={id}")
    public ExamDto getExamByCategoryAndId(@PathVariable("category") String category,
                                                  @PathVariable("id") long id) {
        return examService.findByCategoryAndId(category, id);
    }
    //7. Get all by category and id and year
    @GetMapping("/exams/year={year}/{category}/id={id}")
    public ExamDto getExamByYearAndCategoryAndId(@PathVariable("category") String category,
                                                 @PathVariable("id") long id,
                                                 @PathVariable("year") String year) {
        return examService.findByYearAndCategoryAndId(year, category, id);
    }
}
