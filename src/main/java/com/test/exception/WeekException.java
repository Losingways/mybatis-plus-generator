package com.test.exception;


import com.test.enums.ResponseCodeEnums;

/**
 */
public class WeekException extends RuntimeException {
    public WeekException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public WeekException(ResponseCodeEnums responseCode) {
        this(responseCode.getCode(), ResponseCodeEnums.getEnumMessage(responseCode));
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private String code;

    private String message;
}
