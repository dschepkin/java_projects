package com.dschepkin.javaCore.collection.list;

import java.util.Arrays;
import java.util.List;

/**
 * List.of - не изменяемая коллекция.
 *      Нельзя изменять размер
 *      Нельзя изменять значение
 *
 * Arrrays.asList - нельзя изменять размер, но можно изменять значения.
 *
 */
public class LIstOf {
    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3);
        try {
            integerList.add(4);
        } catch (RuntimeException exception) {
            System.out.println("List.of - is Immutable Collection. You can't increase size");
        }

        try {
            integerList.set(1,10);
        } catch (RuntimeException exception) {
            System.out.println("List.of - is Immutable Collection. You can't cange value");
        }

        List<Integer> list = Arrays.asList(1, 2, 3);

        try {
            list.add(4);
        } catch (RuntimeException exception) {
            System.out.println("Arrays.asList - is Immutable Collection. You can't increase size");
        }

        try {
            list.set(1,10);
            System.out.println("Value added");
        } catch (RuntimeException exception) {
            System.out.println("Arrays.asList - is Immutable Collection. You can't cange value");
        }
    }
}

/**
 * List.of - is Immutable Collection. You can't increase size
 * List.of - is Immutable Collection. You can't cange value
 * Arrays.asList - is Immutable Collection. You can't increase size
 * Value added
 */
