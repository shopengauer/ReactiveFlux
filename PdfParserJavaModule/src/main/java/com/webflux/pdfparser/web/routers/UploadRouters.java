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
    
        mono.flatMap(part -> {
            Map<String, Part> partMap = part.toSingleValueMap();
           List<CreateFileResult> createFileResults = partMap.entrySet().stream().map((partEntry) -> {
                FilePart filePart = (FilePart) partEntry.getValue();
                String fileName = partEntry.getKey();
               try {
                    fileService.createEmptyFile(fileName);
                } catch (IOException e) {
                   return new CreateFileResult(fileName, e.getMessage);
                }
                return new CreateFileResult(fileName);
            })
               .filter(result -> Objects.nonNull(result.info))
               .collect(Collectors.toList());



            return null;
        });

        return ServerResponse.ok().body(BodyInserters.empty());

    }
    
    private static class CreateFileResult{
        String fileName;
        String info;
        
        CreateFileResult(String fileName){
            this.fileName = fileName;
        }
        
        CreateFileResult(String fileName, String info){
             this.fileName = fileName;
             this.info = info;
        }
        
    }
}
