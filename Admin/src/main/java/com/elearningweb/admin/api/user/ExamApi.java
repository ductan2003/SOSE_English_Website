package com.elearningweb.admin.api.user;

import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.service.impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class ExamApi {
    @Autowired
    ExamServiceImpl examService;

    @GetMapping("/all")
    public List<ExamDto> listExams() {
        return examService.findAllExams();
    }

    @GetMapping("/{category}")
    public List<ExamDto> listExamsByCategory(@PathVariable String category) {
        return examService.findAllExamsByCategory(category);
    }


    @GetMapping("/{category}/{id}")
    public List<ExamDto> listExamsByCategoryAndId(@PathVariable String category, @PathVariable long id) {
        return examService.findAllExamsByCategoryAndId(category, id);
    }
}
