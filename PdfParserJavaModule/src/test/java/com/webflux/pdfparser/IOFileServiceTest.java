package com.webflux.pdfparser;


import com.webflux.pdfparser.service.IOFileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IOFileServiceTest {


    @Autowired
    private IOFileService ioFileService;

    @Value("${upload.filepath.base}")
    String baseFilePath;

    @Value("${upload.filepath.test}")
    String testFilePath;

    @Test
    public void createBaseUploadFilePath() throws IOException {
       ioFileService.createBaseDir(baseFilePath);
       ioFileService.createBaseDir(testFilePath);

       ioFileService.createEmptyFileInBaseDir(testFilePath,"test1");
        ioFileService.createEmptyFilesInBaseDir(testFilePath, Arrays.asList("test2","test3"));
         ioFileService.deleteAllFilesInBaseDir(testFilePath);
         ioFileService.deleteBaseDir(testFilePath);

     //   boolean isBaseDirCreated = ioFileService.createBaseDir(baseFilePath);
     // Assert.assertTrue("/Users/vasilij/wordis/uploadfiles",file.getPath());
    }

    @Test
    public void createEmptyFileTest() throws IOException {


      //  File path = ioFileService.getBaseFilesPath(baseFilePath);
      //   ioFileService.createEmptyFileInBaseDir(baseFilePath, "fertu");
    }
}
