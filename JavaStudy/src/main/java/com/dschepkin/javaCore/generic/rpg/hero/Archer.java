package com.dschepkin.javaCore.generic.rpg.hero;

import com.dschepkin.javaCore.generic.rpg.weapon.MeleeWeapon;
import com.dschepkin.javaCore.generic.rpg.weapon.RangeWeapon;

public class Archer<T extends RangeWeapon, V extends MeleeWeapon> extends Hero<T> {

    public Archer(String name, int damage) {
        super(name, damage);
    }
}
