package com.elearningweb.library.service.impl;

import com.elearningweb.library.converter.Converter;
import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.dto.QuestionDto;
import com.elearningweb.library.repository.QuestionRepository;
import com.elearningweb.library.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ExamServiceImpl examService;
    @Autowired
    Converter converter;
    @Override
    public List<QuestionDto> findAllByExam_Id(long exam_id) {
        return questionRepository.findAllByExam_Id(exam_id)
                .stream()
                .map(question -> converter.questionToDto(question)).collect(Collectors.toList());
    }

    @Override
    public QuestionDto findByIdAndExam_Id(long id, long exam_id) {
        return converter.questionToDto(questionRepository.findByIdAndExam_Id(id, exam_id));
    }

    @Override
    public QuestionDto findById(long id) {
        return converter.questionToDto(questionRepository.findById(id));
    }
    @Override
    public Map<Object, Object> evalQuiz(List<String> answers, long id) {
        ExamDto examDto = examService.findById(id);
        List<QuestionDto> questions = examDto.getQuestionsList();

        int correctAnswers = 0;
        int total = questions.size();
        double bandScore;
        Iterator<String> iterator = answers.iterator();
        for (QuestionDto q : questions) {
            if(!iterator.hasNext()) break;
            String answer = iterator.next().trim();
            String correctAnswer = q.getCorrectAnswer().trim();
            if (answer.equals(correctAnswer)) {
                correctAnswers++;
            }
        }

        if (correctAnswers >= 5 && correctAnswers <= 7) {
            bandScore = 2.5;
        } else if (correctAnswers >= 8 && correctAnswers <= 11) {
            bandScore = 3.0;
        } else if (correctAnswers >= 12 && correctAnswers <= 14) {
            bandScore = 3.5;
        } else if (correctAnswers >= 15 && correctAnswers <= 18) {
            bandScore = 4.0;
        } else if (correctAnswers >= 19 && correctAnswers <= 22) {
            bandScore = 4.5;
        } else if (correctAnswers >= 23 && correctAnswers <= 26) {
            bandScore = 5.0;
        } else if (correctAnswers >= 27 && correctAnswers <= 29) {
            bandScore = 5.5;
        } else if (correctAnswers >= 30 && correctAnswers <= 31) {
            bandScore = 6.0;
        } else if (correctAnswers >= 32 && correctAnswers <= 33) {
            bandScore = 6.5;
        } else if (correctAnswers >= 34 && correctAnswers <= 35) {
            bandScore = 7.0;
        } else if (correctAnswers >= 36 && correctAnswers <= 37) {
            bandScore = 7.5;
        } else if (correctAnswers == 38) {
            bandScore = 8.0;
        } else if (correctAnswers == 39) {
            bandScore = 8.5;
        } else if(correctAnswers == 40){
            bandScore = 9.0;
        } else {
            bandScore = 0.0;
        }
        return Map.of("marks", correctAnswers, "total", total, "bandScore", bandScore);
    }
}
