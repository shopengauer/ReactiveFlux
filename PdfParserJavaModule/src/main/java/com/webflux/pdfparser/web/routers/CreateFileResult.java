package com.webflux.pdfparser.web.routers;

import java.io.Serializable;

public class CreateFileResult implements Serializable{

   private String fileName;
   private String info;

    CreateFileResult(String fileName) {
        this.fileName = fileName;
    }

    CreateFileResult(String fileName, String info) {
        this.fileName = fileName;
        this.info = info;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
