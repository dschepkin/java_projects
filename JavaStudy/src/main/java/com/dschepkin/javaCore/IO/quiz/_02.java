package com.dschepkin.javaCore.IO.quiz;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Дан файл с текстом
 * Вывести все слова последняя буква которых равна первой букве следующего слова
 */
public class _02 {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("src", "main", "resources", "file.txt");
        try (Scanner scanner = new Scanner(path)) {
            String prev = null;

            //передвигаем позицию prev на одно слово вперед, если оно вообще есть
            if(scanner.hasNext()) {
                prev = scanner.next();
            }

            while (scanner.hasNext()) {
                String current = scanner.next();

                if(isBothWordOk(prev,current)) {
                    System.out.println(prev + " " + current);
                }

                prev = current;
            }
        }
    }

    private static boolean isBothWordOk(String prev, String current) {
        return prev.charAt(prev.length() - 1) == current.charAt(0);
    }
}
