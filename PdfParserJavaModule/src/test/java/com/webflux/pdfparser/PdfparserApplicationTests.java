package com.webflux.pdfparser;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Mono;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class PdfparserApplicationTests {


    @Autowired
    private RouterFunction uploadRouterFunction;

    private WebTestClient testClient;

    @Before
    public void setUp() throws Exception {
        testClient = WebTestClient.bindToRouterFunction(uploadRouterFunction)
                .configureClient().build();
    }

    @Test
    public void contextLoads() {


        //  testClient.post().accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromMultipartData()).
    }

}
