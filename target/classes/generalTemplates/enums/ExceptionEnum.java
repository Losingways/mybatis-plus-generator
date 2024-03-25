package com.general.enums;



/**
 * 通用返回枚举值
 */
public enum ExceptionEnum {

    LOSINGWAYS_COMMON_EXCEPTION_001("LOSINGWAYS_COMMON_EXCEPTION_001", "信息不存在"),


    ;

    private final String code;

    private final String message;

    private String enMessage = "";

    private String tcMessage = "";

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ExceptionEnum(String code, String message, String enMessage) {
        this.code = code;
        this.message = message;
        this.enMessage = enMessage;
    }

    ExceptionEnum(String code, String message, String tcMessage, String enMessage) {
        this.code = code;
        this.message = message;
        this.tcMessage = tcMessage;
        this.enMessage = enMessage;
    }

    public static String getEnumMessage(ExceptionEnum responseCode) {
        for (ExceptionEnum farmErrorCode : values()) {
            if (farmErrorCode.equals(responseCode)) {
                return farmErrorCode.message;
            }
        }
        return null;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTcMessage() {
        return tcMessage;
    }

    public String getEnMessage() {
        return enMessage;
    }


}
