package com.dschepkin.javaCore.iterator.customList;

import java.util.Iterator;

public class CustomListRunner {
    public static void main(String[] args) {
        CustomList<String> customList = new CustomList<>(3);
        customList.add("String01");
        customList.add("String02");
        customList.add("String03");

        String arrValue1 = customList.getArrValue(0);
        String arrValue2 = customList.getArrValue(1);
        String arrValue3 = customList.getArrValue(2);
        System.out.println(arrValue1);
        System.out.println(arrValue2);
        System.out.println(arrValue3);

        //iterator
        System.out.println();
        System.out.println("Print throw custom iterator foreach:");
        for (String s : customList) {
            System.out.println(s);
        }

        System.out.println("Print throw custom iterator while:");
        Iterator<String> iterator = customList.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
