package com.dschepkin.javaCore.IO.quiz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Дан файл
 * Найти и вывести наиболее число цифр идущих подряд в каждой строке
 * В строке может быть только одно число
 */
public class _03 {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("src", "main", "resources", "file.txt");
        Files.readAllLines(path).stream()
                .map(_03::findMaxCountDigits)
                .forEach(System.out::println);
    }

    private static Integer findMaxCountDigits(String line) {
        System.out.println("I'm in method findMaxCountDigits");
        Integer result = 0;
        int counter = 0;

        for (int i = 0; i < line.length(); i++) {
            if(Character.isDigit(line.charAt(i))) {
                counter++;
            } else {
                if (result < counter) {
                    result = counter;
                }
                counter = 0;
            }

            if (i == line.length() - 1 ) { //если это не сделать, то, если цифра последний символ, не будет результата
                return counter;
            }
        }
        return result;
    }
}
