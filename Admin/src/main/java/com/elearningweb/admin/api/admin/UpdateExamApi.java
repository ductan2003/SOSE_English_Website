package com.elearningweb.admin.api.admin;

import com.elearningweb.library.dto.CategoryDto;
import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.service.impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
public class UpdateExamApi {

    private static Path fileA, fileQ;
    private static final Path staticPath = Paths.get("static");
    private static final Path fileAnswerPath = Paths.get("fileAnswer");
    private static final Path fileQuestionPath = Paths.get("fileQuestion");
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private ExamServiceImpl examService;

    @GetMapping("/all")
    public List<ExamDto> listExams() {
        return examService.findAllExams();
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ExamDto createExam(@RequestPart String title,
                              @RequestPart String category,
                              @RequestPart MultipartFile fileQuestion,
                              @RequestPart MultipartFile fileAnswer
    ) throws IOException {
        initFolder(fileAnswer, fileQuestion);
        ExamDto examDto = new ExamDto();
        examDto.setTitle(title);
        examDto.setCategory(new CategoryDto(null, category));
        examDto.setFileQuestion(fileQ.toString());
        examDto.setFileAnswer(fileA.toString());
        return examService.save(examDto);
    }

    @PutMapping(value = "/update/{id}")
    public ExamDto updateExam(@RequestPart String title,
                              @RequestPart String category,
                              @RequestPart MultipartFile fileQuestion,
                              @RequestPart MultipartFile fileAnswer,
                              @PathVariable("id") long id) throws IOException {
        initFolder(fileAnswer, fileQuestion);
        ExamDto examDto = examService.findById(id);
        if (examDto == null) {
            return null;
        }
        examDto.setTitle(title);
        examDto.setCategory(new CategoryDto(null, category));
        examDto.setFileQuestion(fileQ.toString());
        examDto.setFileAnswer(fileA.toString());
        return examService.save(examDto);
    }

    @DeleteMapping(value = "/delete")
    public void deleteExam(@RequestBody long[] ids) {
        examService.delete(ids);
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
