package com.api.clinica.domain.doctor;

import lombok.Getter;

@Getter
public enum WeekDay {
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private final String day;

    WeekDay(String day) {
        this.day = day;
    }

    public static WeekDay fromDay(String day) {
        for (WeekDay weekDay : WeekDay.values()) {
            if (weekDay.getDay().equals(day)) {
                return weekDay;
            }
        }
        throw new IllegalArgumentException("Unknown day: " + day);
    }
}
