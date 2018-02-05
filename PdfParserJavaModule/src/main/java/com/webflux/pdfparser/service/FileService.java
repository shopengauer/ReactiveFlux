package com.webflux.pdfparser.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {

    public void createEmptyFile(String fileName) throws IOException {
        Files.createFile(Paths.get(fileName));
    }



}
