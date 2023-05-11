package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.QuestionDto;

import com.elearningweb.library.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionServiceImpl questionService;


    @GetMapping("/exam-id={id}")
    public List<QuestionDto> findAllByExam_Id(@PathVariable("id") long id) {
        return questionService.findAllByExam_Id(id);
    }

    @GetMapping("/exam-id{exam-id}/{id}")
    public QuestionDto findByIdAndExam_Id(@PathVariable("id") long id,
                                          @PathVariable("examId") long examId) {
        return questionService.findByIdAndExam_Id(id, examId);
    }

    @PostMapping("/eval-exam")
    public ResponseEntity<?> evalExam(@RequestParam List<String> answers, @RequestParam long examId) {
        return ResponseEntity.ok(questionService.evalQuiz(answers, examId));
    }
}
