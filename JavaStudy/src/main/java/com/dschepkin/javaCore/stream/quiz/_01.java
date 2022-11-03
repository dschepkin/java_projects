package com.dschepkin.javaCore.stream.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * найти среднее всех нечетный чисел делящихся на 5
 */
public class _01 {
    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 5, 10, 15, 20);

        OptionalDouble average = integerList.stream()
                .filter(value -> value % 2 != 0) //находим все нечетные
                .filter(value -> value % 5 == 0) //находим делящиеся на 5 без остатка
                .mapToInt(Integer::intValue) //создаем поток примитивных типов
                .average();//avarage есть только у потока примитиных типов

        average.ifPresent(System.out::println);
    }
}
