package com.webflux.pdfparser.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    public Path createEmptyFile(String fileName) throws IOException {
        return Files.createFile(Paths.get(fileName));
    }



}
