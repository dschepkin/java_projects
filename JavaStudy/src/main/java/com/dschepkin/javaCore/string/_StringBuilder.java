package com.dschepkin.javaCore.string;

/**
 * работает гораздо быстрее при конкатенации больших строк
 * не потокобезопасный
 */
public class _StringBuilder {
    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        String stringConcat = "";
        for (int i = 0; i < 100000; i++) {
            stringConcat += i;
        }
        var end = System.currentTimeMillis();
        var elapsed = end - start;
        System.out.println("Simple concatenation: " + elapsed);

        start = System.currentTimeMillis();
        StringBuilder stringBuiler = new StringBuilder();
        for (int i = 0; i < 100_000; i++) {
            stringBuiler.append(i);
        }
        end = System.currentTimeMillis();
        elapsed = end - start;
        System.out.println("StringBuiler concatenation: " + elapsed);
    }
}

/*
Simple concatenation: 3283
StringBuiler concatenation: 3
*/
