package com.dschepkin.javaCore.string;

/**
 * потокобезопасный
 */

public class _StringBuffer {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("Тест");
        sb.append(" StringBuffer");
        System.out.println(sb);
    }
}
