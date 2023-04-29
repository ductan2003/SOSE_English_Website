package com.elearningweb.library.service.impl;

import com.elearningweb.library.converter.Converter;
import com.elearningweb.library.dto.QuestionDto;
import com.elearningweb.library.repository.QuestionRepository;
import com.elearningweb.library.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
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
}
