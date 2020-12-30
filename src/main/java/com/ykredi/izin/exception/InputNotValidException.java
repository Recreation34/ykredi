package com.ykredi.izin.exception;

public class InputNotValidException extends RuntimeException {
    final private ErrorCode errorCode;

    public InputNotValidException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
