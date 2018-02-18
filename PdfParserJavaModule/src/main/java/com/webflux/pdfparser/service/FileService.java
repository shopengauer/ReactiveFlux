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

    @Value("${upload.base.filepath}")
    String uploadBaseFilePath;

    public Path createEmptyFile(String fileName) throws IOException {
        return Files.createFile(Paths.get(fileName));
    }

    public Path filePathResolver(String fileName){
        return Paths.get(uploadBaseFilePath + fileName);
    }

}
