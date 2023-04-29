package com.elearningweb.library.service.impl;

import com.elearningweb.library.converter.Converter;
import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.model.Category;
import com.elearningweb.library.model.Exam;
import com.elearningweb.library.model.Question;
import com.elearningweb.library.repository.CategoryRepository;
import com.elearningweb.library.repository.ExamRepository;
import com.elearningweb.library.repository.QuestionRepository;
import com.elearningweb.library.service.ExamService;
import com.elearningweb.library.util.ExcelReadingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private Converter converter;
    @Autowired
    private ExcelReadingUtil excelReadingUtil;

    @Override
    public ExamDto save(ExamDto examDto) throws IOException {
        List<Question> questionList = excelReadingUtil.getQuestionListFromExcel(examDto.getFileAnswer());

        Category category = categoryRepository.findByName(examDto.getCategory().getName());
        if (category == null) {
            category = new Category();
            category.setName(examDto.getCategory().getName());
            categoryRepository.save(category);
        }

        Exam exam = examRepository.findById(examDto.getId());

        //CREATE IF EXAM NOT EXIST
        if (exam == null) {
            exam = new Exam();
            for (Question question : questionList) {
                question.setExam(exam);
            }
            exam.setQuestionsList(questionList);
            questionRepository.saveAll(questionList);
        }

        //UPDATE IF EXAM EXISTS
        else {
            Iterator<Question> iterator = questionList.iterator();
            for(Question question: exam.getQuestionsList()) {
                question.setCorrectAnswer(iterator.next().getCorrectAnswer());
                question.setExam(exam);
            }
        }

        exam.setCategory(category);
        exam.setTitle(examDto.getTitle());
        exam.setFileQuestion(examDto.getFileQuestion());
        exam.setFileAnswer(examDto.getFileAnswer());


        examRepository.save(exam);
        return converter.examToDto(exam);
    }

    @Override
    public void delete(long[] ids) {
        for (long item : ids) {
            examRepository.deleteById(item);
        }
    }

    @Override
    public List<ExamDto> findAllExams() {
        List<Exam> exams = examRepository.findAll();
        return converter.listExamToDto(exams);
    }

    @Override
    public List<ExamDto> findAllExamsByCategory(String category) {
        List<Exam> exams = examRepository.findAllByCategory_Name(category);
        return converter.listExamToDto(exams);
    }

    @Override
    public List<ExamDto> findAllExamsByCategoryAndId(String category, long id) {
        return converter.listExamToDto(examRepository.findAllByCategory_NameAndId(category, id));
    }

    @Override
    public ExamDto findById(long id) {
        return converter.examToDto(examRepository.findById(id));
    }
}
