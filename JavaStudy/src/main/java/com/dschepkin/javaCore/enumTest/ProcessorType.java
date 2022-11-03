package com.dschepkin.javaCore.enumTest;

public enum ProcessorType implements Describable {
    BIT_32(32) {
        @Override
        public void printFullInfo() {
            //это реализация абстрактного класса этого перечисления
            System.out.println("I am BIT_32 and my digital is " + getDigital("BIT_32"));
        }

        @Override
        public void print() {
            System.out.println("Это реализация метода интерфейса");
        }
    },
    BIT_64(64) {
        @Override
        public void printFullInfo() {
            //это реализация абстрактного класса этого перечисления
            System.out.println("I am BIT_64 and my digital is " + getDigital("BIT_64"));
        }

        @Override
        public void print() {
            System.out.println("Это реализация метода интерфейса");
        }
    };

    private int digital;

    ProcessorType(int digital) {
        this.digital = digital;
    }

    int getDigital(String name) {
        return digital;
    }

    public abstract void printFullInfo();
}
