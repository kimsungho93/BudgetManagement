package com.ksh.budgetmanagement.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {
    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분 ss초");

    public static  String LocalDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }
}
