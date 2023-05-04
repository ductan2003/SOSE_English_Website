package com.elearningweb.admin.controller.admin;

import com.elearningweb.library.dto.CategoryDto;
import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.service.impl.ExamServiceImpl;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class UpdateExamController {

    private static Path fileA, fileQ;
    private static final Path staticPath = Paths.get("static");
    private static final Path fileAnswerPath = Paths.get("fileAnswer");
    private static final Path fileQuestionPath = Paths.get("fileQuestion");
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private ExamServiceImpl examService;

    @GetMapping("/exams/all")
    public List<ExamDto> listExams() {
        return examService.findAllExams();
    }

    @GetMapping("/exams/{year}")
    public ExamDto getExamByYear(@PathVariable("year") int year) {
        return examService.findByYear(year);
    }
    @GetMapping("/exams/{year}/{category}")
    public ExamDto getExamByYear(@PathVariable("year") int year,
                                 @PathVariable("category") String category) {
        return examService.findByYearAndCategory(year, category);
    }
    @GetMapping("/exams/id={id}")
    public ExamDto getExamById(@PathVariable("id") long id) {
        return examService.findById(id);
    }
    @GetMapping("/exams/{year}/{category}/id={id}")
    public ExamDto getExamByYearAndCategoryAndId(@PathVariable("category") String category,
                                                 @PathVariable("id") long id,
                                                 @PathVariable("year") int year) {
        return examService.findByYearAndCategoryAndId(year, category, id);
    }
    @PostMapping(value = "/exams/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ExamDto createExam(@RequestPart String title,
                              @RequestPart String category,
                              @RequestPart String description,
                              @RequestPart int year,
                              @RequestPart MultipartFile fileQuestion,
                              @RequestPart MultipartFile fileAnswer
    ) throws IOException {
        initFolder(fileAnswer, fileQuestion);
        ExamDto examDto = new ExamDto();
        examDto.setTitle(title);
        examDto.setDescription(description);
        examDto.setYear(year);
        examDto.setCategory(new CategoryDto(null, category));
        examDto.setFileQuestion(fileQ.toString());
        examDto.setFileAnswer(fileA.toString());
        return examService.save(examDto);
    }

    @PutMapping(value = "/exams/update/id={id}")
    public ExamDto updateExam(@RequestPart String title,
                              @RequestPart String category,
                              @RequestPart String description,
                              @RequestPart int year,
                              @Nullable @RequestPart MultipartFile fileQuestion,
                              @Nullable @RequestPart MultipartFile fileAnswer,
                              @PathVariable("id") long id) throws IOException {
        ExamDto examDto = examService.findById(id);
        if (examDto == null) {
            return null;
        } else {
            initFolder(fileAnswer, fileQuestion);
            examDto.setTitle(title);
            examDto.setDescription(description);
            examDto.setYear(year);
            examDto.setCategory(new CategoryDto(null, category));
            examDto.setFileAnswer(fileA.toString());
            examDto.setFileQuestion(fileQ.toString());
        }

        return examService.save(examDto);
    }

    @DeleteMapping(value = "/exams/delete/id={id}")
    public void deleteExam(@PathVariable("id") long id) {
        examService.delete(id);
    }


    public void initFolder(MultipartFile fileAnswer, MultipartFile fileQuestion) throws IOException {
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(fileAnswerPath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(fileAnswerPath));
        }
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(fileQuestionPath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(fileQuestionPath));
        }
        fileA = CURRENT_FOLDER.resolve(staticPath)
                .resolve(fileAnswerPath)
                .resolve(Objects.requireNonNull(fileAnswer.getOriginalFilename()));
        fileQ = CURRENT_FOLDER.resolve(staticPath)
                .resolve(fileQuestionPath)
                .resolve(Objects.requireNonNull(fileQuestion.getOriginalFilename()));
        try (OutputStream os = Files.newOutputStream(fileA)) {
            os.write(fileAnswer.getBytes());
        }
        try (OutputStream os = Files.newOutputStream(fileQ)) {
            os.write(fileQuestion.getBytes());
        }
    }
}