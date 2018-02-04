package com.webflux.pdfparser.web.webconfigs;

import com.webflux.pdfparser.web.routers.UploadRouters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WebRouterConfig {

  @Bean
  public UploadRouters baseRouters(){
      return new UploadRouters();
  }

  @Bean
  public RouterFunction<ServerResponse> uploadRouter(UploadRouters uploadRouters){
      return uploadRouters.uploadRouterFunction();
  }



}
