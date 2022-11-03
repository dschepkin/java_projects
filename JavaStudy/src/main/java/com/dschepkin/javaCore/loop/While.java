package com.dschepkin.javaCore.loop;

public class While {
    public static void main(String[] args) {
        int value = 5;

        //цикл с постуcловием
        do {
            System.out.println("value == " + value); //выполнится хотябы 1 раз
            value++;
        } while (value < 6); //false: 6 < 6

        //value == 5
    }

    private static void simpleWhile(int value) {
        while(value >= 1) {
            System.out.println(value);
            value--;
        }
    }
}
