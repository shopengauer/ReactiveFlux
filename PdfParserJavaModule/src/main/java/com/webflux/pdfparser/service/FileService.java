package com.webflux.pdfparser.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${filepath.base}")
    String baseFilePath;

    public Path baseCreateEmptyFile(String fileName) throws IOException {
        return Files.createFile(baseFilePathResolver(fileName));
    }

    public Path createEmptyFile(String filePath,String fileName) throws IOException {
        return Files.createFile(filePathResolver(filePath, fileName));
    }

    public void baseDeleteFile(String fileName) throws IOException {
         Files.delete(baseFilePathResolver(fileName));
    }

    public Path baseFilePathResolver(String fileName){
        return filePathResolver(baseFilePath,fileName);
    }

    public Path filePathResolver(String filePath, String fileName){
        return Paths.get(filePath + fileName);
    }

}
