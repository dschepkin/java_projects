package com.dschepkin.javaCore.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Современный. Надо использовать его
 *
 * работает с объектов Path, а не File
 * копирование файла
 * создание директорий
 * создание временных директорий
 * симлинков
 */
public class _Files {
    public static void main(String[] args) throws IOException {
        //read file
        Path path = Path.of("src", "main", "resources", "file.txt");
        try (Stream<String> file = Files.lines(path)) {
            file.forEach(System.out::println);
        }

        //write to file
        Path pathWrite = Path.of("src", "main", "resources", "WriteFile.txt");
        Files.write(pathWrite, List.of("The dog say", "Gaf", "Gav"));
    }
}
