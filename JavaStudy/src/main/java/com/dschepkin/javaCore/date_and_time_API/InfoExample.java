package com.dschepkin.javaCore.date_and_time_API;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * java.util.Calendar       - old API (don't use it)
 * java.time.ZonedDateTime  - основной класс с полной информацией о временном контексте (заменил старый)
 * java.time.Instant        - (replace old java.util.Date) представляет точку на временной шкале (не хранит зону)
 * java.time.LocalDate      - представление только даты без времени и зоны (replace java.sql.Date)
 * java.time.LocalTime      - только время без даты и зоны (replace java.sql.Time)
 * java.time.LocalDateTime  - дата и время без зоны (replace java.sql.Timestamp)
 * java.time.OffsetTime     - время и зона без даты
 * java.time.OffsetDateTime - время, зона и дата
 * java.time.Period         - Длительность: календарная длительность (период) в виде кортежа (год, месяц, день)
 * java.time.Daration       - Длительность: в виде целого кол-ва секунд и долей текущей секунды в виде наносекунд
 * java.time.format.DateTimeFormatter - определяет настройки форматирования и парсинга
 *
 * Все классы date time API
 */

public class InfoExample {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        long epochMilli = now.toInstant().toEpochMilli();
        now.plus(1L, ChronoUnit.DAYS); //расширенный механизм
        ZonedDateTime plusOneDay = now.plusDays(1); //явно увеличить на 1 день
        ZonedDateTime zonedDateTime = now.truncatedTo(ChronoUnit.DAYS); //обнулить на начало дня

        //можно составить объект добавляя отдельные части
        LocalDateTime of = LocalDateTime.of(2022, 01, 1, 12, 59, 00);

    }
}
