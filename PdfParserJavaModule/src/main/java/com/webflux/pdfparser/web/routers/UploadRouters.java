package com.webflux.pdfparser.web.routers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Consumer;

public class UploadRouters {

    @Value("${web.props.uploadpath}")
    private String uploadPath;

    public RouterFunction<ServerResponse> uploadRouterFunction() {
        return RouterFunctions.route(RequestPredicates.POST(uploadPath), this::uploadHandler);
    }


    private Mono<ServerResponse> uploadHandler(ServerRequest serverRequest) {
        Mono<MultiValueMap<String, Part>> mono = serverRequest.body(BodyExtractors.toMultipartData());
        mono.flatMap(part -> {
            Map<String, Part> partMap = part.toSingleValueMap();
            partMap.entrySet().stream().forEach(new Consumer<Map.Entry<String, Part>>() {
                @Override
                public void accept(Map.Entry<String, Part> stringPartEntry) {
                    String fileName = stringPartEntry.getKey();
                   // FilePart

                }
            });

            return null;
        });

        return ServerResponse.ok().body(BodyInserters.empty());

    }

}
