package com.elearningweb.library.converter;

import com.elearningweb.library.dto.CategoryDto;
import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.dto.QuestionDto;
import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.Category;
import com.elearningweb.library.model.Exam;
import com.elearningweb.library.model.Question;
import com.elearningweb.library.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {
    //Convert to DTO
    public QuestionDto questionToDto(Question question) {
        QuestionDto result = new QuestionDto();

        result.setId(question.getId());
        result.setTitle(question.getTitle());
        result.setQuestionId(question.getQuestionId());
        result.setSection(question.getSection());
        result.setParagraph(question.getParagraph());
        result.setQuestion(question.getQuestion());
        result.setCorrectAnswer(question.getCorrectAnswer());
        return result;
    }

    public ExamDto examToDto(Exam exam) {
        ExamDto result = new ExamDto();
        List<QuestionDto> questionDtoList = this.listQuestionToDto(exam.getQuestionsList());

        result.setId(exam.getId());
        result.setTitle(exam.getTitle());
        result.setDescription(exam.getDescription());
        result.setYear(exam.getYear());
        result.setFileQuestion(exam.getFileQuestion());
        result.setCategory(categoryToDto(exam.getCategory()));
        result.setQuestionsList(questionDtoList);
        result.setFileImage(exam.getFileImage());

        return result;
    }

    public CategoryDto categoryToDto(Category category) {
        CategoryDto result = new CategoryDto();

        result.setId(category.getId());
        result.setName(category.getName());
        return result;
    }

    public List<ExamDto> listExamToDto(List<Exam> list) {
        return list.stream().map(this::examToDto).collect(Collectors.toList());
    }

    public List<QuestionDto> listQuestionToDto(List<Question> list) {
        return list.stream().map(this::questionToDto).collect(Collectors.toList());
    }

    public UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setProfileImage(user.getProfileImage());
        return userDto;
    }

    public List<UserDto> listUserToDto(List<User> list) {
        return list.stream().map(this::userToDto).collect(Collectors.toList());
    }

    //Convert to Entity

    public User userToEntity(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
    public Question questionToEntity(QuestionDto question) {
        Question result = new Question();

        result.setId(question.getId());
        result.setTitle(question.getTitle());
        result.setQuestionId(question.getQuestionId());
        result.setSection(question.getSection());
        result.setParagraph(question.getParagraph());
        result.setQuestion(question.getQuestion());
        result.setCorrectAnswer(question.getCorrectAnswer());
        return result;
    }
    public List<Question> listQuestionToEntity(List<QuestionDto> list) {
        return list.stream().map(this::questionToEntity).collect(Collectors.toList());
    }
}
