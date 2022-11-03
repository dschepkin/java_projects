package com.dschepkin.javaCore.IO;

import java.io.*;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * Reader может работать только с текстом, в отличии от InputStream, который работает с потоком byte
 * работает с массивом char
 */
public class _Reader {
    public static void main(String[] args) throws IOException {
        File file = Path.of("src", "main", "resources", "file.txt").toFile();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String fileText = bufferedReader.lines() //получаем поток
                    .collect(Collectors.joining("\n")); //коллектив все строки через разделитель - перевод строки
            System.out.println(fileText);
        }
    }
}
