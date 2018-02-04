package com.webflux.pdfparser.web.webconfigs;

import com.webflux.pdfparser.web.UploadFileSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Configuration
public class WebFunctionalConfig implements WebFilter, WebFluxConfigurer {

    Logger logger = LoggerFactory.getLogger(WebFunctionalConfig.class);

 //   @Autowired
 //   private UploadFileSystemService uploadFileSystemService;


    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        System.out.println(request.getRemoteAddress().getAddress().getCanonicalHostName());
        return request.getURI().getPath().equals("/") ?
                webFilterChain.filter(serverWebExchange.mutate().request(request.mutate().path("/index.html").build()).build()) :
                webFilterChain.filter(serverWebExchange);
    }

    @Bean
    public RouterFunction<ServerResponse> router(UploadFileSystemService uploadFileSystemService) {
        return RouterFunctions.route(RequestPredicates.POST("/upload"),
                serverRequest -> serverRequest.body(BodyExtractors.toMultipartData()).flatMap(parts -> {
                    Map<String, Part> partMap = parts.toSingleValueMap();
                    logger.info("MultiPart map: {}", partMap);
                    FilePart filePart = (FilePart) partMap.get("Learning Dart.pdf");

                  //  uploadFileSystemService.saveMultipartWithPath(partMap,"");

                    try {
                        Files.createFile(Paths.get(filePart.filename()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        return ServerResponse.noContent().build();

                    }
                    Mono<Void> voidMono = filePart.transferTo(new File(filePart.filename()));
                    return ServerResponse.ok().body(BodyInserters.fromObject("OK"));
                })).and(RouterFunctions.resources("/**", new ClassPathResource("static/web/")));

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/upload")
                .allowedOrigins("*");
    }
}
