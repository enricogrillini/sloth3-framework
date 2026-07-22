package it.eg.sloth.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class DateUtil {

    private DateUtil() {
        // NOP
    }

    public static final ZoneId LOCAL_ZONE_ID = ZoneId.of("Europe/Rome");

    public static String format(LocalDateTime localeDateTime, String format) {
        if (localeDateTime == null) {
            return null;
        }

        return DateTimeFormatter.ofPattern(format).format(localeDateTime);
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

    // Converte data e ora da LocalDataTime to OffsetDateTime
    public static OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        } else {
            return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
        }
    }

    // Converte data e ora da OffsetDateTime to LocalDataTime
    public static LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime value) {
        return value != null
                ? value.atZoneSameInstant(LOCAL_ZONE_ID).toLocalDateTime()
                : null;
    }

}
