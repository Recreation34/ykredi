package com.ykredi.izin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    FIELD_VALIDATION_ERROR(1001, ResourceBundle.getBundle("messages").getString("1001"), HttpStatus.BAD_REQUEST),
    CONTENT_NOT_FOUND_ERROR(1002, ResourceBundle.getBundle("messages").getString("1002"), HttpStatus.BAD_REQUEST),
    HAS_NO_LEAVE_RIGHT(1003, ResourceBundle.getBundle("messages").getString("1003"), HttpStatus.BAD_REQUEST),
    LEAVE_DAY_MUST_BE_BIGGER_THAN_ZERO(1004, ResourceBundle.getBundle("messages").getString("1004"), HttpStatus.BAD_REQUEST),
    LEAVE_STARTING_DATE_CAN_NOT_BE_A_PASSED_DAY(1005, ResourceBundle.getBundle("messages").getString("1005"), HttpStatus.BAD_REQUEST),
    ALREADY_HAVE_LEAVE_IN_THIS_DATE(1006, ResourceBundle.getBundle("messages").getString("1006"), HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
