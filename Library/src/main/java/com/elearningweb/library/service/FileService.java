package com.elearningweb.library.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    String updateFile(String path, MultipartFile multipartFile) throws Exception;
    InputStream getResource(String path, String filename) throws Exception;
}
