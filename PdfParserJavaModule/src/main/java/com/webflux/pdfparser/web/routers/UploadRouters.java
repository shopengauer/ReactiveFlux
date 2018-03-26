package com.webflux.pdfparser.web.routers;

import com.webflux.pdfparser.service.IOFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class UploadRouters {

    @Value("${upload.url}")
    private String uploadPath;

    @Value("${upload.filepath.base}")
     private String childPath;

    private IOFileService fileService;
    private final UploadHandlers uploadHandlers;

    @Autowired
    public UploadRouters(IOFileService fileService, UploadHandlers uploadHandlers) {
        this.fileService = fileService;
        this.uploadHandlers = uploadHandlers;
    }

    /**
     * @return
     */
    @Bean
    @Profile("lambda")
    public RouterFunction<ServerResponse> uploadLambdaRouterFunction() {
        long first = System.currentTimeMillis();
        return RouterFunctions.route(RequestPredicates.POST(uploadPath), serverRequest -> serverRequest.body(BodyExtractors.toMultipartData())
                .flatMap(parts -> {
            Map<String, Part> partMap = parts.toSingleValueMap();
            List<CreateFileResult> fileResults = partMap.entrySet().stream().map(partEntry -> {
                FilePart filePart = (FilePart) partEntry.getValue();
                String fileName = partEntry.getKey();
                try {
                    File file = fileService.createEmptyFileInBaseDir(childPath,fileName);
                    filePart.transferTo(file);
                } catch (IOException e) {
                    return new CreateFileResult(fileName, e.toString());
                }
                return new CreateFileResult(fileName);
            }).collect(Collectors.toList());
            System.out.println(System.currentTimeMillis() - first);
            return ServerResponse.ok().body(BodyInserters.fromObject(fileResults));
        }))
                .and(RouterFunctions.resources("/**", new ClassPathResource("static/web/")));
    }

    @Bean
    @Profile("ref")
    public RouterFunction<ServerResponse> uploadRefRouterFunction() {
        return RouterFunctions.route(RequestPredicates.POST("/upload"), uploadHandlers::uploadHandler
        ).and(RouterFunctions.resources("/**", new ClassPathResource("static/web/")));
    }


}
