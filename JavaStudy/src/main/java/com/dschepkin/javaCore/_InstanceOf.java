package com.dschepkin.javaCore;

public class _InstanceOf {
    public static void main(String[] args) {
        Animal dog = new Dog("Fox", "Mammal");
        Animal fish = new Animal("Fish");

        checkAnimal(dog);
        System.out.println();
        checkAnimal(fish);
    }

    private static void checkAnimal(Animal animal) {
        if(animal instanceof Dog) {
            animal.walk();
        } else {
            System.out.println(animal.type + " - It is not Animal");
        }
    }
}

class Animal {
    String type;

    Animal(String type) {
        this.type = type;
    }

    void walk() {
        System.out.println(type + " walk");
    }
}

class Dog extends Animal {
    String name;

    Dog(String name, String type) {
        super(type);
        this.name = name;
    }

    @Override
    void walk() {
        System.out.println(type + " " + name + " walk");
    }
}
