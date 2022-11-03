package com.dschepkin.javaCore.loop;

/**
 * Метод по вычислению факториала
 */
public class Quiz01Factorial {
    public static void main(String[] args) {
        int input = 10;
        System.out.println(factorial1(input));
        System.out.println(factorial2(input));
        System.out.println(factorial3(input));
    }

    private static int factorial1(int value) {
        int result = value;

        for(int i = value - 1; i >= 1; i--) {
            result *= i;
        }

        return result;
    }

    private static int factorial2(int value) {
        int result = 1;

        for(int i = 1; i <= value; i++) {
            result *= i;
        }

        return result;
    }

    private static int factorial3(int value) {
        int result = 1;
        int i = 1;

        while (i <= value) {
            result *= i;
            i++;
        }

        return result;
    }
}
