package com.webflux.pdfparser.web.routers;

import com.webflux.pdfparser.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class UploadRouters {

    @Value("${web.props.uploadpath}")
    private String uploadPath;

    private FileService fileService;

    @Autowired
    public UploadRouters(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> uploadRouterFunction() {
        return RouterFunctions.route(RequestPredicates.POST("/upload"), serverRequest -> serverRequest.body(BodyExtractors.toMultipartData())
                .flatMap(parts -> {
            Map<String, Part> partMap = parts.toSingleValueMap();
            List<CreateFileResult> fileResults = partMap.entrySet().stream().map(partEntry -> {
                FilePart filePart = (FilePart) partEntry.getValue();
                String fileName = partEntry.getKey();
                try {
                    Files.createFile(Paths.get(fileName));
                    filePart.transferTo(new File(fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    return new CreateFileResult(fileName, e.toString());

                }
                return new CreateFileResult(fileName);
            }).collect(Collectors.toList());
            return ServerResponse.ok().body(BodyInserters.fromObject(fileResults));
        }))
                .and(RouterFunctions.resources("/**", new ClassPathResource("static/web/")));
    }

    /**
     * @param serverRequest
     * @return
     */
    // @Bean
    public Mono<ServerResponse> uploadHandler(ServerRequest serverRequest) {
        serverRequest.body(BodyExtractors.toMultipartData())
                .flatMap(part -> {
                    Map<String, Part> partMap = part.toSingleValueMap();
                    partMap.entrySet().stream().map((partEntry) -> {
                        FilePart filePart = (FilePart) partEntry.getValue();
                        String fileName = partEntry.getKey();
                        try {
                            fileService.createEmptyFile(fileName);
                            filePart.transferTo(new File(fileName));
                        } catch (IOException e) {
                            return new CreateFileResult(fileName, e.getMessage());
                        }
                        return new CreateFileResult(fileName);
                    });
                    return ServerResponse.ok().body(BodyInserters.fromObject(""));

                });

        return ServerResponse.ok().body(BodyInserters.fromObject(""));

    }


}
