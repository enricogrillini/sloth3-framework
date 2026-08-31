package it.eg.sloth.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class OffsetDateTimeUtil {

    private OffsetDateTimeUtil() {
        // NOP
    }

    // Converte data e ora da OffsetDateTime to LocalDataTime
    public static LocalDateTime toLocalDateTime(OffsetDateTime value) {
        return value != null
                ? value.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
                : null;
    }

}
