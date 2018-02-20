package com.webflux.pdfparser;

import com.webflux.pdfparser.service.FileService;
//import org.assertj.core.util.Files;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


import java.nio.file.Path;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebClientUploadTests {

    @Value(value = "${web.address}")
    private String serverAddress;

    @Value("${web.props.uploadpath}")
    private String uploadPath;

    @Value("${filename.test}")
    private String testFilename;

    @Value("${filepath.test}")
    private String testFilepath;

    private WebClient webClient;
    @Autowired
    private FileService fileService;

    @Before
    public void setUp() throws Exception {
        webClient = WebClient.create(serverAddress);
        fileService.baseDeleteFile(testFilename);
    }

    @Test
    public void propertiesVerify() {
        Assert.assertNotNull(serverAddress);
        Assert.assertNotNull(uploadPath);
    }

    @Test
    public void uploadFileTest() throws Exception {

       Path path = fileService.createEmptyFile(testFilepath, testFilename);

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part(testFilename, new FileSystemResource(path.toFile()));
        MultiValueMap<String, HttpEntity<?>> multiValueMap = multipartBodyBuilder.build();


       ResponseEntity<List> entity = webClient.post().uri(uploadPath).accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(multiValueMap)).exchange()
                .flatMap(clientResponse -> {
                    System.out.println(clientResponse.toEntity(List.class));
                    return clientResponse.toEntity(List.class);
                }).block();

        System.out.println(entity.getBody());

    }
}
