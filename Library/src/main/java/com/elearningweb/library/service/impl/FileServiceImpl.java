package com.elearningweb.library.service.impl;

import com.elearningweb.library.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    public static Path path;
    private static final Path staticPath = Paths.get("static");
    public static final Path fileAnswerPath = Paths.get("fileAnswer");
    public static final Path fileQuestionPath = Paths.get("fileQuestion");
    public static final Path fileImagePath = Paths.get("fileImage");
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Override
    public String updateFile(String path, MultipartFile multipartFile) throws Exception {
        String filename = multipartFile.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(filename.substring(filename.lastIndexOf(".")));
        String filePath = path + File.separator + fileName1;

        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String filename) throws Exception {
        String fullPath = path + File.separator + filename;
        InputStream is = new FileInputStream((fullPath));
        return is;
    }


    @Override
    public void init() {
        try {
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(fileAnswerPath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(fileAnswerPath));
            }
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(fileQuestionPath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(fileQuestionPath));
            }
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(fileImagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(fileImagePath));
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, Path filePath) {
        try {
            if(filePath.equals(fileQuestionPath)) {
                path = CURRENT_FOLDER.resolve(staticPath)
                        .resolve(filePath)
                        .resolve(Objects.requireNonNull(file.getOriginalFilename()));
            }
            else if(filePath.equals(fileAnswerPath)) {
                path = CURRENT_FOLDER.resolve(staticPath)
                        .resolve(filePath)
                        .resolve(Objects.requireNonNull(file.getOriginalFilename()));
            }
            else if(filePath.equals(fileImagePath)) {
                path = CURRENT_FOLDER.resolve(staticPath)
                        .resolve(filePath)
                        .resolve(Objects.requireNonNull(file.getOriginalFilename()));
            }
            if (!Files.exists(path)) {
                Files.copy(file.getInputStream(), path);
            }
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }
}
