package com.dschepkin.javaCore.stream;

import java.util.Comparator;

/**
 * Функциональный интерфейс Comparator один метод compare сделаем пример на его основе
 */
public class LambdaExample {
    public static void main(String[] args) {
        /**
         * пример использования
         */
        Comparator<Integer> comparator1 = new IntegerComparator();
        int result = comparator1.compare(5, 10);
        System.out.println(result);

        /**
         * До 1.8 обычной практикой было создания анонимных классов, пример
         */

        Comparator<Integer> comparator2 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        int result2 = comparator2.compare(10,10);
        System.out.println(result2);

        /**
         * Всё изменилось с 1.8
         *
         * Старый вариант можно трансформировать
         */

        //т.к compare единственный у Comparator, то не обязательно указывать какой класс надо создавать
        Comparator<Integer> newComparator1 = (Integer o1, Integer o2) -> {
            return Integer.compare(o1,o2);
        };

        //тк видим тип интерфейса, то можем убрать типы у параметров
        Comparator<Integer> newComparator2 = (o1, o2) -> {
            return Integer.compare(o1,o2);
        };

        //убираем return потому что значем что метод возвращает что-то
        Comparator<Integer> newComparator3 = (o1, o2) -> Integer.compare(o1,o2);

        /**
         * Замена лямбды ссылкой на метод.
         * Мы говорим, что знаем что у метода compare интерфейса Comparator 2 параметра
         *      и у метода Integer.compare 2 параметра, и как бы опускает упоминание метода compare интерфейса Comparator
         *
         * Integer::compare
         *      Interger - класс в котором находится метод compare
         */

        Comparator<Integer> newComparator4 = Integer::compare;
    }

    private static class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1,o2);
        }
    }
}
