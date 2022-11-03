package com.dschepkin.javaCore.loop;

/**
 * Посчитать сумму чисел
 */
public class Quiz02SumDigital {
    public static void main(String[] args) {
        int input = 12345;
        int result = sum(input);
        System.out.println(result);
    }

    private static int sum(int value) {
        int result = 0;
        /**
         * Прибавляем к результату остаток от деления
         * На каждой итерации value теряет один разряд
         */
        while (value > 0) {
            result = result + value % 10;
            value /= 10;
        }
        return result;
    }
}
