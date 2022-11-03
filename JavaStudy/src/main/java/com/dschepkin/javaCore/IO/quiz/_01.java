package com.dschepkin.javaCore.IO.quiz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 *  Дан файл с текстом
 *  Найти и напечатать на консоль все слова начинающиеся с гласной буквы
 */
public class _01 {
    public static final String LOWELS = "aeui";

    public static void main(String[] args) throws IOException {

        Path path = Path.of("src", "main", "resources", "file.txt");
        try (Scanner scanner = new Scanner(path)) { //считываем по слову из файла
            while(scanner.hasNext()) {
                String word = scanner.next();
                char firstSymbol = word.charAt(0);
                if(LOWELS.indexOf(firstSymbol) != -1) {
                    System.out.println(word);
                }
            }
        }
    }
}
