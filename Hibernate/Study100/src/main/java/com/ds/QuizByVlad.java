package com.ds;

/*
Leetcode - 1672. Richest Customer Wealth
Дан двумерный массив целых чисел
Найти наибольшую сумму чисел вложенного массива
 */
public class QuizByVlad {
    public static void main(String[] args) {
        int[][] inputArr = {
                {1,2,3},
                {4,5,6},
                {7,9}
        };

        int result = maximumWealth(inputArr);
    }

    public static int maximumWealth(int[][] accounts) {
        int[] resultArr = new int[accounts.length];

        for(int i = 0; i < accounts.length; i++) {
            int sum = 0;
            for(int j = 0; j < accounts[i].length; j++) {
                sum = sum + accounts[i][j];

                System.out.print(accounts[i][j]);
            }
            System.out.print(" " + sum);
            resultArr[i] = sum;
            System.out.println("");
        }

        int maxSum = resultArr[0];
        for(int i = 0; i < resultArr.length; i++) {
            if (resultArr[i] > maxSum) {
                maxSum = resultArr[i];
            }
        }

        System.out.println("Max value = " + maxSum);

        return maxSum;
    }
}
