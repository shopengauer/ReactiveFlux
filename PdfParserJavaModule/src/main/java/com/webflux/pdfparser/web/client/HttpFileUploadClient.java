package com.webflux.pdfparser.web.client;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.Path;
import java.util.List;

@Service
public class HttpFileUploadClient {


    /**
     *  Http client for send files to server
     *
     * @param paths - list of files paths
     * @param hostAddress - host server address
     * @param uploadUrl - url for upload
     */
    public ResponseEntity<List> sendFilesToServer(List<Path> paths, String hostAddress, String uploadUrl) {
        WebClient webClient = WebClient.create(hostAddress);
        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        paths.forEach(path -> multipartBodyBuilder.part(path.getFileName().toString(), new FileSystemResource(path.toFile())));
        MultiValueMap<String, HttpEntity<?>> multiValueMap = multipartBodyBuilder.build();

        ResponseEntity<List> entity = webClient.post().uri(uploadUrl).accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(multiValueMap)).exchange()
                .flatMap(clientResponse -> {
                    System.out.println(clientResponse.toEntity(List.class));
                    return clientResponse.toEntity(List.class);
                }).block();
         return entity;
    }


}
