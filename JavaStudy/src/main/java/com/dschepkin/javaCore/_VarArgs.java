package com.dschepkin.javaCore;

/**
 * все что есть в списке
 * список представляется в виде массива
 */

public class _VarArgs {
    public static void main(String[] args) {
        int val1 = 5;
        int val2 = 50;
        printValue(val1, val2);

        System.out.println();

        //или с использованием массива
        int[] arr = new int[]{val1, val2};
        printValueArr(arr);
    }

    private static void printValueArr(int[] arr) {
        for(int val : arr) {
            System.out.println(val);
        }
    }

    private static void printValue(int... value) {
        for(int val : value) {
            System.out.println(val);
        }
    }
}
