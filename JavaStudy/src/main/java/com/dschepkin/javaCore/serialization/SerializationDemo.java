package com.dschepkin.javaCore.serialization;

import java.io.*;
import java.nio.file.Path;

/**
 * Чтобы можно было сериализовать класс он должен реализовывать интерфейс метку Serialazable
 *      или более сложный интерфейс
 * Если мы не ходим сериализовать какой-то атрибут, то помечаем его ключевым словом transient
 *      private transient String address;
 * У каждого класса есть версия определяемая константой serialVersionUID, которая изменяется при изменении класса
 * Если класс был изменен (добавлен метод или поле) между сериализацией и десериализацией, то
 * при десериалзиции будет ошибка, и чтобы ее не было задаем постоянно значение этой константе:
 *      public static final long serialVersionUID = 1L;
 *
 * Статические поля не сериализуются (кроме serialVersionUID);
 */
public class SerializationDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path path = Path.of("src", "main", "resources", "serialization.out");
        Person dima = new Person(20, "Dima", "Voronezh");

        serialization(path,dima);
        deserialization(path);
    }

    private static void deserialization(Path path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            Object person = (Person) objectInputStream.readObject();
            System.out.println(person);
        }
    }

    private static void serialization(Path path, Object object) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(object);
        }
    }
}
