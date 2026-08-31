package it.eg.sloth.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class LocalDateUtil {

    private LocalDateUtil() {
        // NOP
    }

    public static String format(LocalDateTime localeDateTime, String format) {
        if (localeDateTime == null) {
            return null;
        }

        return DateTimeFormatter.ofPattern(format).format(localeDateTime);
    }

    public static LocalDate min(LocalDate... dates) {
        return Arrays.asList(dates).stream().filter(Objects::nonNull) // Evita NullPointerException
                .min(LocalDate::compareTo).orElse(null); // Ritorna null se la lista è vuota
    }

    public static LocalDate max(LocalDate... dates) {
        return Arrays.asList(dates).stream().filter(Objects::nonNull) // Evita NullPointerException
                .max(LocalDate::compareTo).orElse(null); // Ritorna null se la lista è vuota
    }

    // Converte data e ora da LocalDataTime to OffsetDateTime
    public static OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        } else {
            return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
        }
    }

    // Calcola il prossimo giorno lavorativo
    public static LocalDate getNextBusinessDays(LocalDate localeDate, int daysToSubtract) {
        if (localeDate == null) {
            return null;
        }

        LocalDate currDate = localeDate;
        int addedDays = 0;
        while (addedDays < daysToSubtract) {
            currDate = currDate.plusDays(1);

            if (!isWeekend(currDate) && !isFixedHoliday(currDate)) {
                addedDays++;
            }
        }
        return currDate;
    }

    // Calcola il precedente giorno lavorativo
    public static LocalDate getPreviousBusinessDays(LocalDate localeDate, int daysToSubtract) {
        if (localeDate == null) {
            return null;
        }

        LocalDate currDate = localeDate;
        int subtractedDays = 0;
        while (subtractedDays < daysToSubtract) {
            currDate = currDate.minusDays(1);

            if (!isWeekend(currDate) && !isFixedHoliday(currDate)) {
                subtractedDays++;
            }
        }
        return currDate;
    }

    static boolean isWeekend(LocalDate localDate) {
        if (localDate == null) {
            return false;
        }

        return localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    static boolean isFixedHoliday(LocalDate localDate) {
        if (localDate == null) {
            return false;
        }

        return localDate.getMonth() == Month.JANUARY && localDate.getDayOfMonth() == 1 || // Capodanno
                localDate.getMonth() == Month.JANUARY && localDate.getDayOfMonth() == 6 ||// Epifania
                localDate.getMonth() == Month.APRIL && localDate.getDayOfMonth() == 25 ||// Festa della Liberazione
                localDate.getMonth() == Month.MAY && localDate.getDayOfMonth() == 1 || // Festa dei Lavoratori
                localDate.getMonth() == Month.JUNE && localDate.getDayOfMonth() == 2 || // Festa della Repubblica
                localDate.getMonth() == Month.AUGUST && localDate.getDayOfMonth() == 15 || // Ferragosto
                localDate.getMonth() == Month.NOVEMBER && localDate.getDayOfMonth() == 1 || // Ognissanti
                localDate.getMonth() == Month.DECEMBER && localDate.getDayOfMonth() == 8 || // Immacolata Concezione
                localDate.getMonth() == Month.DECEMBER && localDate.getDayOfMonth() == 25 || // Natale
                localDate.getMonth() == Month.DECEMBER && localDate.getDayOfMonth() == 26 || // Santo Stefano
                localDate.equals(calculateEasterSunday(localDate.getYear())) ||     // Pasqua
                localDate.equals(calculateEasterSunday(localDate.getYear()).plusDays(1));    // Lunedì di Pasqua
    }

    public static LocalDate calculateEasterSunday(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int month = (h + l - 7 * m + 114) / 31;
        int day = ((h + l - 7 * m + 114) % 31) + 1;

        return LocalDate.of(year, month, day);
    }

}
