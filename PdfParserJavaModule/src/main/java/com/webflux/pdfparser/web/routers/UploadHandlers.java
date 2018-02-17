package com.webflux.pdfparser.web.routers;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UploadHandlers {

    public Mono<ServerResponse> uploadHandler(ServerRequest serverRequest) {
        Mono<ServerResponse> responseMono = serverRequest.body(BodyExtractors.toMultipartData())
                .flatMap(parts -> {
                    Map<String, Part> partMap = parts.toSingleValueMap();
                    List<CreateFileResult> fileResults = partMap.entrySet().stream().map(partEntry -> {
                        FilePart filePart = (FilePart) partEntry.getValue();
                        String fileName = partEntry.getKey();
                        try {
                            Files.createFile(Paths.get(fileName));
                            filePart.transferTo(new File(fileName));
                        } catch (IOException e) {
                            return new CreateFileResult(fileName, e.toString());
                        }
                        return new CreateFileResult(fileName);
                    }).collect(Collectors.toList());
                    return ServerResponse.ok().body(BodyInserters.fromObject(fileResults));
                });
        return responseMono;
    }
}
