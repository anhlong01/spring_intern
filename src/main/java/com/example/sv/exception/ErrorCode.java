package com.example.sv.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    STUDENT_NOT_FOUND(404, "Student with this ID not found", HttpStatus.NOT_FOUND),
    SUBJECT_NOT_FOUND(404, "Not found any subject of the student with this id", HttpStatus.NOT_FOUND),
    RECORD_EXIST(1001, "The record with this key already exists", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}