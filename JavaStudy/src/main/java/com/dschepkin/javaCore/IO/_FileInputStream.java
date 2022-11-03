package com.dschepkin.javaCore.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class _FileInputStream {
    public static void main(String[] args) throws IOException {
        File file = Path.of("src", "main", "resources", "file.txt").toFile();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] fileAllBytes = fileInputStream.readAllBytes();
            String fileText = new String(fileAllBytes);
            System.out.println(fileText);
        }
    }
}
