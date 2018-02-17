package com.webflux.pdfparser.web.routers;

import com.webflux.pdfparser.service.FileService;
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
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class UploadRouters {

    @Value("${web.props.uploadpath}")
    private String uploadPath;

    private FileService fileService;
    private final UploadHandlers uploadHandlers;

    @Autowired
    public UploadRouters(FileService fileService, UploadHandlers uploadHandlers) {
        this.fileService = fileService;
        this.uploadHandlers = uploadHandlers;
    }

    /**
     * @return
     */
    @Bean
    @Profile("lambda")
    public RouterFunction<ServerResponse> uploadLambdaRouterFunction() {
        return RouterFunctions.route(RequestPredicates.POST("/upload"), serverRequest -> serverRequest.body(BodyExtractors.toMultipartData())
                .flatMap(parts -> {
            Map<String, Part> partMap = parts.toSingleValueMap();
            List<CreateFileResult> fileResults = partMap.entrySet().stream().map(partEntry -> {
                FilePart filePart = (FilePart) partEntry.getValue();
                String fileName = partEntry.getKey();
                try {
                    Path path = fileService.filePathResolver(fileName);
                    Files.createFile(path);
                    filePart.transferTo(new File(path.toString()));
                } catch (IOException e) {
                    return new CreateFileResult(fileName, e.toString());
                }
                return new CreateFileResult(fileName);
            }).collect(Collectors.toList());
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
