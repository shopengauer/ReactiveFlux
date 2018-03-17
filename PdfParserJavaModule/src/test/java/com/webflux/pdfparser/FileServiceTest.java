package com.webflux.pdfparser;

import com.webflux.pdfparser.service.FileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    Logger LOG = LoggerFactory.getLogger(FileServiceTest.class);

    @Value("${upload.filename.test}")
    private String testFilename;

    private List<String> testFilenames = Arrays.asList("testfile1.xml"
            , "testfile2.xml", "testfile3.xml", "testfile4.xml");


    @Autowired
    private FileService fileService;


//    @Before
//    public void setUp() {
//        Files.delete(fileService.baseFilePathResolver(testFilename).toFile());
//    }

    @Test
    public void createAndDeleteTestFiles() throws IOException {
      //  fileService.createEmptyFilesInBasePath(testFilenames);
        fileService.deleteAllFilesInBasePath();
        Assert.assertTrue(fileService.isBaseFilePathEmpty());
      //  getClass().getClassLoader().getResource("/uploadfiles/1.xml")

        ;
     // File[] file = new ClassPathResource("/uploadfiles").getFile().listFiles();
     //   System.out.println();

    }
}
