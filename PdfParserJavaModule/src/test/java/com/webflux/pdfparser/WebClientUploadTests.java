package com.webflux.pdfparser;

import com.webflux.pdfparser.service.FileService;
import com.webflux.pdfparser.web.client.HttpFileUploadClient;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//import org.assertj.core.util.Files;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebClientUploadTests {

    private static Logger LOG = LoggerFactory.getLogger(WebClientUploadTests.class);

    @Value(value = "${web.props.hostaddress}")
    private String hostAddress;

    @Value("${upload.url}")
    private String uploadUrl;

    @Value("${upload.filename.test}")
    private String testFilename;

    private List<String> testFilenamesForUpload = Arrays.asList("testfile1.xml"
            ,"testfile2.xml", "testfile3.xml", "testfile4.xml");

    @Value("${upload.filepath.test}")
    private String testFilepath;

    private WebClient webClient;

    @Autowired
    private FileService fileService;

    @Autowired
    private HttpFileUploadClient httpFileUploadClient;


    @Before
    public void setUp() throws Exception {
        webClient = WebClient.create(hostAddress);
        fileService.deleteFileInBasePath(testFilename);
    }

    @Test
    public void propertiesVerify() {
        Assert.assertNotNull(hostAddress);
        Assert.assertNotNull(uploadUrl);
        Assert.assertNotNull(testFilenamesForUpload);
    }

    @Test
    public void uploadFileTest() throws Exception {



        Path path = fileService.createEmptyFile(testFilepath, testFilename);

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part(path.getFileName().toString(), new FileSystemResource(path.toFile()));
        MultiValueMap<String, HttpEntity<?>> multiValueMap = multipartBodyBuilder.build();


        ResponseEntity<List> entity = webClient.post().uri(uploadUrl).accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(multiValueMap)).exchange()
                .flatMap(clientResponse -> {
                    System.out.println(clientResponse.toEntity(List.class));
                    return clientResponse.toEntity(List.class);
                }).block();

        System.out.println(entity.getBody());

    }

    @Test
    public void httpClientFileUploadTest() throws IOException {

        List<Path> paths = new ArrayList<>();
        for (String fileName : testFilenamesForUpload) {
            try {
                paths.add(fileService.createEmptyFile(testFilepath, fileName));
            } catch (IOException e) {
                LOG.error("Error creating file: {}", e.getMessage());
            }
        }

     ResponseEntity<List> response =
             httpFileUploadClient.sendFilesToServer(paths,hostAddress,uploadUrl);

     assert response!=null;

    }
}
