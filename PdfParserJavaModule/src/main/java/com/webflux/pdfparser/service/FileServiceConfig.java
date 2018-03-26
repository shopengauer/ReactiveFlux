package com.webflux.pdfparser.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileServiceConfig {

    @Bean
    public IOFileService ioFileService(){
        return new IOFileService();
    }


}
