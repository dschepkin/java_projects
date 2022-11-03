package com.dschepkin.javaCore.generic.rpg.hero;

import com.dschepkin.javaCore.generic.rpg.weapon.MeleeWeapon;

public class Warior<T extends MeleeWeapon> extends Hero<T>{

    public Warior(String name, int damage) {
        super(name, damage);
    }
}
