package com.elearningweb.library.service.impl;

import com.elearningweb.library.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String updateFile(String path, MultipartFile multipartFile) throws Exception {
        String filename = multipartFile.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(filename.substring(filename.lastIndexOf(".")));
        String filePath = path + File.separator + fileName1;

        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String filename) throws Exception {
        String fullPath=path+File.separator+filename;
        InputStream is=new FileInputStream((fullPath));
        return is;
    }
}
