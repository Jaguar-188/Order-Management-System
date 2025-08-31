package com.example.haveIt.entity.exception;

import java.time.LocalDateTime;

public class ExceptionMessage {

    private String message;
    private String urlPath;
    private String statusCode;
    private String statusLine;
    private LocalDateTime localDateTime;

    public ExceptionMessage(String message, String urlPath, String statusCode, String statusLine, LocalDateTime localDateTime) {
        this.message = message;
        this.urlPath = urlPath;
        this.statusCode = statusCode;
        this.statusLine = statusLine;
        this.localDateTime = localDateTime;
    }

    public ExceptionMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "ExceptionMessage{" +
                "message='" + message + '\'' +
                ", urlPath='" + urlPath + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", statusLine='" + statusLine + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
