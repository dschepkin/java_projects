package com.dschepkin.javaCore.IO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class _FileOutputStream {
    public static void main(String[] args) throws IOException {
        File file = Path.of("src", "main", "resources", "OutputFile.txt").toFile();
        //добавляем, а не перезаписываем
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file,true))) {
            String text = "Java super lang!";
            bufferedOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
//            bufferedOutputStream.write("\n'".getBytes()); //можно сделать так, но лучше использовать готовый сепаратор
            bufferedOutputStream.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8)); //
        }
    }
}
