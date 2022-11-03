package com.dschepkin.javaCore;

public class Test {
    int id;
    String name;

    public Test(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int add(int value1, int value2) {
        return value1 + value2;
    }
}
