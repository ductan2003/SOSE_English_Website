package com.elearningweb.library.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface FileService {
    String updateFile(String path, MultipartFile multipartFile) throws Exception;
//    String updateFileImage(MultipartFile multipartFile) throws IOException;
    InputStream getResource(String path, String filename) throws Exception;
    void init();
    void save(MultipartFile file, Path filePath);
}
