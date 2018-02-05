package com.webflux.pdfparser.web.routers;

import org.springframework.http.HttpStatus;

public class HttpError {

    HttpStatus httpStatus;
    String message;

    public HttpError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
