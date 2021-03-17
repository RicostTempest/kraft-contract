package com.windsoft.kraft.contract.common.utils;

import java.util.Random;

public class RandomUtils {
    public static final String STRING_UP = "ABCDEFGHIJKLMNOPQRETUVWXYZ";
    public static final String STRING_LOW = "abcdefghijklmnopqretuvwxyz";
    public static final String STRING_NUMBER = "1234567890";
    public static final String STRING_SIGN = "~!@#$%^&*:";

    /**
     * 生成salt
     * @param n
     * @return
     */
    public static String getSalt(int n){
        StringBuffer buffer = new StringBuffer();
        buffer.append(STRING_UP);
        buffer.append(STRING_LOW);
        buffer.append(STRING_NUMBER);
        buffer.append(STRING_SIGN);
        return code(buffer, n);
    }

    public static String getPassword(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(STRING_NUMBER);
        return code(buffer,6);
    }

    public static String getCaptcha(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(STRING_NUMBER);
        return code(buffer, 8);
    }


    /**
    * 生成随机字符串
    */
    public static String code(StringBuffer buffer, int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = buffer.charAt(new Random().nextInt(buffer.length()));
            sb.append(aChar);
        }
        return sb.toString();
    }
}
