package com.dschepkin.library;

import java.util.function.BiFunction;
/**
 * Есть класс Person для которого будут рассчитываться выплаты за overtime часы, а также за больничные дни
 * мы делаем методы рассчета и в параметре передаем интерфейсы
 * затем пример с использование лямбды
 * затем делаем общий интерфейс с обощенными типами
 * затем показано, что не надо изобретать велосипед, а надо использовать готовые функциональные интерфейсы, как
 * пример BiFunction
 *
 * java.util.function
 * */
public class Lambda_03_function {
    public static void main(String[] args) {
        var runner = new Lambda_03_function();
        var person = new PersonV("Dima", 2);
        Integer countHospitalDay = 10;
        Double countOvertimeHours = 1.5;

        //#1 используем анонимные классы
//        var payForHospitalDays = runner.processHospital(person, countHospitalDay, new ProcessHospital() {
//            @Override
//            public double process(int grade, int countHospitalDay) {
//                return person.getGrade() * countHospitalDay * 0.5;
//            }
//        });
//
//        var payForOvertimeHours = runner.processOvertime(person, countOvertimeHours, new ProcessOvertime() {
//            @Override
//            public double process(int grade, double countOvertime) {
//                return person.getGrade() * countOvertimeHours * 2;
//            }
//        });
        //#2 используем lambda
        var payForHospitalDays = runner.processHospital(person, countHospitalDay, (g, d) -> person.getGrade() * countHospitalDay * 0.5);
        var payForOvertimeHours = runner.processOvertime(person, countOvertimeHours, (g, h) ->person.getGrade() * countOvertimeHours * 2);

        System.out.println(payForHospitalDays+" rub for hospital "+ countHospitalDay +" days");
        System.out.println(payForOvertimeHours+" rub for overtime "+ countOvertimeHours +" hours");
    }
    //оба метода используют разные конкретные интерфейсы
//    //рассчитываем выплату за больничный. Больничный считается целыми днями
//    public double processHospital(PersonV p, int countHospitalDay, ProcessHospital ph) {
//        //write to db
//        //send email
//        return ph.process(p.getGrade(), countHospitalDay);
//    }
//    //рассчитываем выплату за перерабоки. Переработки могу быть не полными часами, а 1.5 hours
//    public double processOvertime(PersonV p, double countOvertime, ProcessOvertime po) {
//        //write to db
//        //send email
//        return po.process(p.getGrade(), countOvertime);
//    }

    //используем интерфейс с generic
//    public Double processHospital(PersonV p, int countHospitalDay,Process<Integer, Integer, Double> ph) {
//        //write to db
//        //send email
//        return ph.process(p.getGrade(), countHospitalDay);
//    }
//    //рассчитываем выплату за перерабоки. Переработки могу быть не полными часами, а 1.5 hours
//    public double processOvertime(PersonV p, double countOvertime, Process<Integer,Double,Double> po) {
//        //write to db
//        //send email
//        return po.process(p.getGrade(), countOvertime);
//    }
    //используем общий готовый функциональные интерфейс BiFunction
    public Double processHospital(PersonV p, int countHospitalDay, BiFunction<Integer, Integer, Double> ph) {
        //write to db
        //send email
        return ph.apply(p.getGrade(), countHospitalDay);
    }
    //рассчитываем выплату за перерабоки. Переработки могу быть не полными часами, а 1.5 hours
    public double processOvertime(PersonV p, double countOvertime, BiFunction<Integer,Double,Double> po) {
        //write to db
        //send email
        return po.apply(p.getGrade(), countOvertime);
    }
}

interface ProcessHospital {
    double process(int grade, int countDay);
}

interface ProcessOvertime {
    double process(int grade, double countOvertime);
}

interface Process <P1, P2, R> {
    R process(P1 p1, P2 p2);
}

class PersonV {
    private String name;
    private Integer grade;

    public PersonV(String name, Integer grade) {
        this.name = name;
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }
}
