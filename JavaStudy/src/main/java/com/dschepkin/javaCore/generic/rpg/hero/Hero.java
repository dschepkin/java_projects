package com.dschepkin.javaCore.generic.rpg.hero;

import com.dschepkin.javaCore.generic.rpg.weapon.Weapon;

public abstract class Hero<T extends Weapon> {
    private String name;
    private int baseDamage;

    public Hero(String name, int damage) {
        this.name = name;
        this.baseDamage = damage;
    }

    private T weapon;

    public T getWeapon() {
        return weapon;
    }

    public void setWeapon(T weapon) {
        this.weapon = weapon;
    }
}
