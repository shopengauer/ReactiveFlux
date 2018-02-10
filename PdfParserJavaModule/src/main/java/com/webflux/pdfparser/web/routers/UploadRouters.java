package com.webflux.pdfparser.web.routers;

import com.webflux.pdfparser.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class UploadRouters {

    @Value("${web.props.uploadpath}")
    private String uploadPath;

    private FileService fileService;

    @Autowired
    public UploadRouters(FileService fileService) {
      this.fileService = fileService;
    }

    /**
     *
     * @return
     */
    public RouterFunction<ServerResponse> uploadRouterFunction() {
        return RouterFunctions.route(RequestPredicates.POST(uploadPath), this::uploadHandler);
    }

    /**
     *
     * @param serverRequest
     * @return
     */
    private Mono<ServerResponse> uploadHandler(ServerRequest serverRequest) {
        Mono<MultiValueMap<String, Part>> mono = serverRequest.body(BodyExtractors.toMultipartData());
        Mono<ServerResponse> serverResponseMono = ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        List<HttpError> httpErrors = new ArrayList<>();
        mono.flatMap(part -> {
            Map<String, Part> partMap = part.toSingleValueMap();
           List<HttpError> errors = partMap.entrySet().stream().map((partEntry) -> {
                FilePart filePart = (FilePart) partEntry.getValue();
                try {
                    fileService.createEmptyFile(partEntry.getKey());

                } catch (IOException e) {
                   return new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "IOError");
                }
                return null;
            }).collect(Collectors.toList());



            return null;
        });

        return ServerResponse.ok().body(BodyInserters.empty());

    }

}