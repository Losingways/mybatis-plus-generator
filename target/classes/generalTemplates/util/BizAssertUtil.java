package com.general.util;

import com.general.enums.ExceptionEnum;
import com.general.exception.WeekException;

/**
 * 断言工具类
 */
public class BizAssertUtil {

    /**
     * 是
     * @param expression 表达式
     * @param exceptionEnum  异常code
     */
    public static void isFalse(boolean expression, ExceptionEnum exceptionEnum) {
        if (!expression) {
            throw new WeekException(exceptionEnum.getCode(),exceptionEnum.getMessage());
        }
    }

    /**
     * 否
     * @param expression 表达式
     * @param exceptionEnum  异常code
     */
    public static void isTrue(boolean expression, ExceptionEnum exceptionEnum) {
        if (expression) {
            throw new WeekException(exceptionEnum.getCode(),exceptionEnum.getMessage());
        }
    }



}
