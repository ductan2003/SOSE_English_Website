package com.elearningweb.admin.controller.user;

import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.service.impl.ExamServiceImpl;
import com.elearningweb.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class ExamController {
    @Autowired
    ExamServiceImpl examService;
    @Autowired
    UserServiceImpl userService;
    //1.Get all exams
    @GetMapping("/exams/all")
    public ResponseEntity<?> listExams() {

        List<ExamDto> list = examService.findAllExams();
        Map<Object, Object> map = Map.of("total", list.size(), "listPost", list);
        return ResponseEntity.ok(map);
    }
    //2.Get exam by id
    @GetMapping("/exams/id={id}")
    public ExamDto getExamById(@PathVariable("id") long id) {
        return examService.findById(id);
    }
    //3.Get all by category
    @GetMapping("/exams/{category}")
    public ResponseEntity<?> listExamsByCategory(@PathVariable("category") String category) {
        List<ExamDto> list = examService.findAllExamsByCategory(category);
        Map<Object, Object> map = Map.of("total", list.size(), "listPost", list);
        return ResponseEntity.ok(map);
    }
    //4. Get all by year
    @GetMapping("/exams/year={year}")
    public ResponseEntity<?> getExamByYear(@PathVariable("year") String year) {
        List<ExamDto> list = examService.findAllByYear(year);
        Map<Object, Object> map = Map.of("total", list.size(), "listPost", list);
        return ResponseEntity.ok(map);
    }
    //5. Get all by year and category
    @GetMapping("/exams/year={year}/{category}")
    public ResponseEntity<?> getExamByYearAndCategory(@PathVariable("year") String year,
                                 @PathVariable("category") String category) {
        List<ExamDto> list = examService.findAllByYearAndCategory(year, category);
        Map<Object, Object> map = Map.of("total", list.size(), "listPost", list);
        return ResponseEntity.ok(map);
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
