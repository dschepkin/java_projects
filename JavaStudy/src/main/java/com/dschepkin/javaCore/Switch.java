package com.dschepkin.javaCore;


public class Switch {
    public static void main(String[] args) {
        //Можно использовать типы: byte, short, int, char, String, enum

        switchOld(2);
//        value == 2 OR 3

        //если забыть поставить break
//        value == 2 OR 3
//        value == 10
    }

    /**
     * улучшенный синтаксис: без break (но можно и его использовать)
     * может работать как expression
     */
    private static char switchNew(int value) {
        char result;
        result = switch(value) {
            case 1 -> '1';
            case 2,3 -> '2';
            default -> '0';
        };

        return result;
    }

    private static void switchOld(int value) {
        switch (value) {
            case 1:
                System.out.println("value == 1");
                break;
            case 2:
            case 3:
                System.out.println("value == 2 OR 3");
//                break;
            case 10:
                System.out.println("value == 10");
                break;
            default:
                System.out.println("Нет такого значения");
        }
    }
}
