package com.dschepkin.javaCore.pattern.builer;

/**
 *
 * 1 Method overloading
 *
 * public void foo(String a, Integer b) {
 * }
 *
 * public void foo(String a) {
 *     foo(a, 0);
 * }
 *
 * foo("a", 2);
 * foo("a");
 *
 * One of the limitations of this approach is that it doesn’t work if you have two optional parameters of the same type
 * and any of them can be omitted.
 *
 * 2 method
 * The attribute b of class Entity, should have default value = 0, if assigned
 * EntityBuiler implemented it
 */

public class EntityBuilerExample {
    public static void main(String[] args) {
        Entity entity = new EntityBuilder().setA(5).builer();
        System.out.println(entity);
    }

}

class EntityBuilder {
    private int a;
    private int b = 0;

    public EntityBuilder setA(int a) {
        this.a = a;
        return this;
    }

    public EntityBuilder setB(int b) {
        this.b = b;
        return this;
    }

    Entity builer() {
        return new Entity(a, b);
    }
}

class Entity {
    private int a;
    private int b;

    public Entity(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
