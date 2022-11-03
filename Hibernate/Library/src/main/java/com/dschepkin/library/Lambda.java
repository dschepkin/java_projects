package com.dschepkin.library;

import java.io.File;
import java.io.FileFilter;

public class Lambda {
    public static void main(String... args) {
        //#1 В этом примере добавляем отдельный класс 'Filter' с фильтрацией - show only directory
//        File location = new File(".");
//        var files = location.listFiles(new Filter());
//        for (File file : files) {
//            System.out.println(file.getName());
//        }

        //#2 next use anonimous class
//        File pathname = new File(".");
//        var files = pathname.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.isDirectory();
//            }
//        });
//        for (File file : files) {
//            System.out.println(file.getName());
//        }
        //#3 use lambda
        var pathname = new File(".");
        var files = pathname.listFiles(f -> f.isDirectory());
        //более короткая запись - var files = pathname.listFiles(File::isDirectory);
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}

class Filter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory();
    }
}
