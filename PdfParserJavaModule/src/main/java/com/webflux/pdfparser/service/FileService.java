package com.webflux.pdfparser.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Service for work with files
 *
 * @author vasilij
 */
public class FileService {

    Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${upload.filepath.base}")
    String baseFilePath;

    /**
     * Create empty file in base path
     *
     * @param fileName - file name
     * @return Path of created file
     * @throws IOException
     */
    public Path createEmptyFileInBasePath(String fileName) throws IOException {
        return Files.createFile(baseFilePathResolver(fileName));
    }

    /**
     * Create empty files in base path
     * @param fileNames file name
     * @return List of Path
     * @throws IOException
     */
    public List<Path> createEmptyFilesInBasePath(List<String> fileNames) throws IOException {
       return createEmptyFilesInPath(baseFilePath, fileNames);
    }

    /**
     * Create empty file in a given path
     *
     * @param filePath - file path
     * @param fileName - file name
     * @return Path of created file
     * @throws IOException
     */
    public Path createEmptyFile(String filePath, String fileName) throws IOException {
        return Files.createFile(filePathResolver(filePath, fileName));
    }

    /**
     * Create empty files in given path
     *
     * @param filesPath - path of files
     * @param fileNames - list of filenames
     * @return list of Path
     * @throws IOException
     */
    public List<Path> createEmptyFilesInPath(String filesPath, List<String> fileNames) throws IOException {
        List<Path> paths = new ArrayList<>();
        for (String fileName : fileNames) {
            paths.add(Files.createFile(filePathResolver(filesPath,fileName)));
        }
        return paths;
    }

    /**
     * Delete file in base path
     *
     * @param fileName - file name
     * @throws IOException
     */
    public void deleteFileInBasePath(String fileName) throws IOException {
        Files.deleteIfExists(baseFilePathResolver(fileName));
    }

    /**
     * Delete file in given path
     *
     * @param fileName - file name
     * @param filePath - file path
     * @throws IOException
     */
    public void deleteFileInPath(String fileName, String filePath) throws IOException {
        Files.deleteIfExists(filePathResolver(filePath, fileName));
    }

    /**
     * Delete all files in base path
     */
    public void deleteAllFilesInBasePath() throws IOException {
        deleteAllFilesInPath(baseFilePath);
    }

    /**
     * Удаление всех файлов в заданной директории
     * @param filePath - директория для очистки
     */
    public void deleteAllFilesInPath(String filePath) throws IOException {
        Iterator<Path> filesForDelete = Files.newDirectoryStream(Paths.get(filePath)).iterator();
        while(filesForDelete.hasNext()){
            Path path = filesForDelete.next();
            Files.deleteIfExists(path);
        }
    }

    /**
     * Get Path for given filename in base path
     *
     * @param fileName - file name
     * @return Path
     */
    public Path baseFilePathResolver(String fileName) throws IOException {
        return filePathResolver(baseFilePath, fileName);
    }

    /**
     * Get Path for given filename and path
     *
     * @param filePath - file path
     * @param fileName - file name
     * @return
     */
    public Path filePathResolver(String filePath, String fileName) throws IOException {
        return Paths.get(filePath + fileName);
    }


}
