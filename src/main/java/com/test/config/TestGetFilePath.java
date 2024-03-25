package com.test.config;

import java.io.File;

/**
 * @author losingways
 * @Description
 */
public class TestGetFilePath {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getFile());
        System.out.println(ClassLoader.getSystemResource("").getFile());
        System.out.println(System.getProperty("user.dir") );
        System.out.println(System.getProperty("user.dir") + File.separator + "bin");


    }
}
