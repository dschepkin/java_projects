package com.dschepkin.javaCore.stream.quiz;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Дан список строк.
 * Найти кол-во уникальных строк длинной более 8 символов
 */
public class _02 {
    public static void main(String[] args) {
        List<String> stringList = List.of(
                "aaaaaaaaaa",
                "bbbbbbbbbbbbbbb",
                "ccccccccccccccccc",
                "ccccccccccccccccc",
                "ccccccccccccccccc",
                "aaa",
                "dd"
        );

        int countUniqStrings = stringList.stream()
                .filter(value -> value.length() > 8)
                .collect(Collectors.toSet())
                .size();

        System.out.println(countUniqStrings);

        /**
         * Or
         */

        stringList.stream()
                .filter(value -> value.length() > 8)
                .distinct() //этобы это работало у объекта должны быть корректно сделать методы equals and hashCode
                .count();

    }
}
