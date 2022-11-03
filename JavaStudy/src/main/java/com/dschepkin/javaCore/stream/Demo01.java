package com.dschepkin.javaCore.stream;

import java.util.Arrays;

public class Demo01 {
    public static void main(String[] args) {
        String[] arrString = {"a","b","c"};

        Arrays.stream(arrString)
                .map(value -> value + "a")
                .forEach(System.out::println);
    }
}
