package com.webflux.pdfparser;

import com.webflux.pdfparser.service.FileService;
import org.assertj.core.util.Files;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    Logger logger = LoggerFactory.getLogger(FileServiceTest.class);

    @Value("${test.tesfilename}")
    private String testFilename;

    @Autowired
    private FileService fileService;


    @Before
    public void setUp() {
        Files.delete(fileService.baseFilePathResolver(testFilename).toFile());
    }

    @Test
    public void createAndDeleteTestFile() {
        Path testFilePath = fileService.baseFilePathResolver(testFilename);
        File file = Files.newFile(testFilePath.toString());
        FileSystemResource resource = new FileSystemResource(file);
        Assert.assertTrue(resource.isFile());
        Assert.assertNotNull(Files.contentOf(file, Charset.defaultCharset()));
        Files.delete(file);
        logger.info("Create file path: {}",resource.getPath());

    }
}
