package com.legermano.allevents.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime convertStrinToLocalDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);
        return LocalDateTime.parse(s, formatter);
    }
}
