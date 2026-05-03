package it.eg.sloth.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class DateUtil {

    private DateUtil() {
        // NOP
    }

    public static String format(LocalDateTime ora, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return ora.format(formatter);
    }

    public static LocalDate min(LocalDate... dates) {
        return Arrays.asList(dates).stream()
                .filter(Objects::nonNull) // Evita NullPointerException
                .min(LocalDate::compareTo)
                .orElse(null); // Ritorna null se la lista è vuota
    }

    public static LocalDate max(LocalDate... dates) {
        return Arrays.asList(dates).stream()
                .filter(Objects::nonNull) // Evita NullPointerException
                .max(LocalDate::compareTo)
                .orElse(null); // Ritorna null se la lista è vuota
    }

}
