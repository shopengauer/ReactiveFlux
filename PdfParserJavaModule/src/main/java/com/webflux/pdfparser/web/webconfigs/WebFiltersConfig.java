package com.webflux.pdfparser.web.webconfigs;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class WebFiltersConfig implements WebFilter{

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        System.out.println(request.getRemoteAddress().getAddress().getCanonicalHostName());
        return request.getURI().getPath().equals("/") ?
                webFilterChain.filter(serverWebExchange.mutate().request(request.mutate().path("/index.html").build()).build()) :
                webFilterChain.filter(serverWebExchange);
    }
}
