package com.dschepkin.javaCore.loop;

public class Quiz03FactorialRecursion {
    public static void main(String[] args) {
        int input = 4;
        int result = factorialRecursion(input);
        System.out.println(result);
    }

    /**
     * Рекурсивные ф-ции разворачиваем с конца, т.е сразу определяем выход ( когда == 1 )
     */
    private static int factorialRecursion(int value) {
        if(value == 1) {
            return 1;
        }
//        4 * (3 * (2 * 1))) = 24
        return value * factorialRecursion(value - 1);
    }
}
