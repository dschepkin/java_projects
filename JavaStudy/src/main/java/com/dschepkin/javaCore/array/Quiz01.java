package com.dschepkin.javaCore.array;

/**
 * Написать программу печатающую массив сначала в прямом порядке, а затем в обратном
 */

public class Quiz01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        printArrDescAscOrder(arr);

        //лучше разделить на 2 метода
        printArrAscOrder(arr);
        System.out.println();
        printArrDescOrder(arr);
    }

    /**
     * один метод для печати на консоль массива в прямом и обратном порядке
     */

    private static void printArrDescAscOrder(int[] arr) {
        String resultAscOrder = "" + arr[0];
        String resultDescOrder = "" + arr[arr.length -1];

        for(int i = 1; i < arr.length; i++) {
            String arrAscOrder = "" + arr[i];
            String arrDescOrder = "" + arr[arr.length -i -1];

            resultAscOrder = resultAscOrder + "" + arrAscOrder;
            resultDescOrder = resultDescOrder + "" + arrDescOrder;
        }

        System.out.println(resultAscOrder);
        System.out.println(resultDescOrder);
    }

    private static void printArrAscOrder(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "");
        }
    }

    private static void printArrDescOrder(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[arr.length -i -1]);
        }
    }
}
