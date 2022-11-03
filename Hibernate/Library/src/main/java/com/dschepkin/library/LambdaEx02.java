package com.dschepkin.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaEx02 {
    public static void main(String... args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(String.format("%02d",i));
        }
        //#1 сортировка в обратном порядке use anonimous class
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return -o1.compareTo(o2);
//            }
//        });
        //#2 таже сортировка строк, но с использованием lambda
        Collections.sort(list,(o1, o2) -> -o1.compareTo(o2));

        for (String s : list) {
            System.out.println(s);
        }
    }
}
