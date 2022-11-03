package com.dschepkin.javaCore.generic.rpg;

import com.dschepkin.javaCore.generic.rpg.hero.Archer;
import com.dschepkin.javaCore.generic.rpg.hero.Hero;
import com.dschepkin.javaCore.generic.rpg.hero.Warior;
import com.dschepkin.javaCore.generic.rpg.weapon.Bow;
import com.dschepkin.javaCore.generic.rpg.weapon.MeleeWeapon;
import com.dschepkin.javaCore.generic.rpg.weapon.Sword;
import com.dschepkin.javaCore.generic.rpg.weapon.Weapon;

public class GameRunner {
    public static void main(String[] args) {
        Archer<Bow,Sword> legolas = new Archer<>("Legolas", 1);
        legolas.setWeapon(new Bow());
        System.out.println("Bow attack - " + legolas.getWeapon().getDamage() + " damage");

        Warior<Sword> boromir = new Warior<>("Boromir", 3);
        boromir.setWeapon(new Sword());
        System.out.println("Sword attack - " + boromir.getWeapon().getDamage() + " damage");

        /**
         * Generic for method
         */
        printHeroDamage1(boromir);
        printHeroDamage2(legolas);
    }

    /**
     *  Метод printHeroDamage1:
     *      Hero<T>             - метод принимает тип Hero параметризованный каким-то типом
     *      <T extends Weapon>  - на уровне метода определяем какой же тип это будет.
     *                              Все что ниже по иерарзии от Weapon
     */
    private static <T extends Weapon> void printHeroDamage1(Hero<T> hero) {
        System.out.println(hero.getWeapon().getDamage());
    }

    /**
     *  методы printHeroDamage1 и printHeroDamage2 одинаковы отличается только описание generic
     *  но ? - wildcard дает нам возможность работать вверх по иерархии
     *
     *  super   - используется для consumers
     *              принимает какой-то объект
     *              setWeapon(new Sword()) - т.е ссылка параметризованного типа потребляет объект
     *                                       как бы мы устанавливаем/задаем по ссылке типа
     *  extends - используется для producers
     *              отдает
     *              hero.getWeapon() - по ссылке параметризованного типа мы получаем что-то
     */

    private static void printHeroDamage2(Hero<? extends Weapon> hero) {
        System.out.println(hero.getWeapon().getDamage());
    }

    private static void printHeroDamage3(Hero<? super Sword> hero) {
        hero.setWeapon(new Sword());
    }

    private static void printHeroDamage4(Hero<? extends Sword> hero) {
        Sword weapon = hero.getWeapon();
    }

}
