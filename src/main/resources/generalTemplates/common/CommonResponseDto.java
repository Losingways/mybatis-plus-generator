package com.general.common;

import com.general.enums.ResponseCodeEnums;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结果集
 */
@Data
public class CommonResponseDto<T> implements Serializable {
    private static final long serialVersionUID = 3978461145132490879L;
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据 T 支持任意类型返回数据
     */
    private T data;


    public CommonResponseDto(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 公共成功状态码，仅输入返回数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto success(T data) {
        return new CommonResponseDto(ResponseCodeEnums.SUCCESS.getCode(),
                ResponseCodeEnums.SUCCESS.getMessage(), data);
    }


    /**
     *  公共成功状态码，输入返回数据、返回消息
     * @param data
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto success(T data, String msg) {
        return new CommonResponseDto(ResponseCodeEnums.SUCCESS.getCode(), msg, data);
    }

    /**
     * 生成快速成功返回对象
     * @return
     */
    public static CommonResponseDto success() {
        return success(null);
    }

    /**
     * 失败携带返回对象
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto fail(T data) {
        return new CommonResponseDto(ResponseCodeEnums.FAIL.getCode(),
                ResponseCodeEnums.FAIL.getMessage(), data);
    }

    /**
     * 快速失败
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto fail() {
        return fail(null);
    }

    /**
     * 异常，携带自定义消息
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto error(String msg) {
        return new CommonResponseDto(ResponseCodeEnums.FAIL.getCode(), msg, null);
    }

    /**
     * 异常， 携带状态码和自定义返回消息
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto error(String code, String msg) {
        return new CommonResponseDto<T>(code, msg, null);
    }

    /**
     * 异常
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto error(String code, String msg, T data) {
        return new CommonResponseDto<T>(code, msg, data);
    }

    /**
     * 异常 自定义状态码
     * @param responseCode
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto error(ResponseCodeEnums responseCode) {
        return new CommonResponseDto(responseCode.getCode(), responseCode.getMessage(), null);
    }

    /**
     * 判断是否是处理成功
     * @param result
     * @return
     */
    public static boolean isSuccess(CommonResponseDto<?> result) {
        return result != null && ResponseCodeEnums.SUCCESS.getCode().equals(result.getCode());
    }
}