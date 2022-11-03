package com.dschepkin.javaCore.IO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class _File {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/file.txt");
        boolean createFileResult = file.createNewFile();
        System.out.println("Is file created successfully - " + createFileResult);

        /**
         * в разных ОС разделитель путь в ФС отличается
         *      win \
         *      lin /
         * поэтому путь до файлы "src/main/resources/file.txt" будет не всегда валидным
         * можно использовать новый класс Path
         */

        File file2 = Path.of("src", "main", "resources", "file.txt").toFile();

        /**
         * еще есть более громоздкий вариант использования через separator
         */

        File file3 = new File(String.join(File.separator, "src", "main", "resources", "file.txt"));


    }
}
