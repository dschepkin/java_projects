package com.nokia;

public class Main {
    void getOuterText() {
        System.out.println("Outer class.");
    }
    public class Inner {
        public Main outer() {
            System.out.println("Link to Outer object");
            return Main.this;
        }
    }
    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Main.Inner inner = main.inner();
        inner.outer().getOuterText();
    }
}