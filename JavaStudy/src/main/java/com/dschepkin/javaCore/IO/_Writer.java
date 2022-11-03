package com.dschepkin.javaCore.IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class _Writer {
    public static void main(String[] args) throws IOException {
        File file = Path.of("src", "main", "resources", "writerOutputFile.txt").toFile();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.append("Again Java is super!");
            bufferedWriter.newLine(); //в этом методе реализован System.lineSeparator
            bufferedWriter.append("It not all...");
        }
    }
}
