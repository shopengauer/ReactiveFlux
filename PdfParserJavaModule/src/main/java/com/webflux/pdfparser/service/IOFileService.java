package com.webflux.pdfparser.service;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class IOFileService {


    private File getBasePath(String childPath) {
        return FileUtils.getFile(new File(FileUtils.getUserDirectoryPath(), childPath));
    }

    public boolean createBaseDir(String childPath) {
        return !getBasePath(childPath).exists() && getBasePath(childPath).mkdirs();
    }

    public File createEmptyFileInBaseDir(String childPath, String fileName) throws IOException {
        File file = FileUtils.getFile(getBasePath(childPath), fileName);
        boolean isCreate = file.createNewFile();
        return file;
    }

    public void createEmptyFilesInBaseDir(String childPath, List<String> fileNames) throws IOException {
        for (String fileName : fileNames) {
            createEmptyFileInBaseDir(childPath, fileName);
        }
    }

    public boolean deleteFileInBaseDir(String childPath, String fileName) {
        File file = FileUtils.getFile(getBasePath(childPath), fileName);
        return file.delete();
    }

    public void deleteFilesInBaseDir(String childPath, List<String> fileNames) {
        for (String fileName : fileNames) {
            deleteFileInBaseDir(childPath, fileName);
        }
    }

    public void deleteAllFilesInBaseDir(String childPath) throws IOException {
        FileUtils.cleanDirectory(getBasePath(childPath));
    }

    public boolean deleteBaseDir(String childPath) {
        return FileUtils.getFile(getBasePath(childPath)).delete();
    }


}
