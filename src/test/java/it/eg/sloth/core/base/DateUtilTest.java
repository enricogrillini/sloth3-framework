package it.eg.sloth.core.base;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Project: sloth3-framework
 * Copyright (C) 2022-2025 Enrico Grillini
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 *
 * @author Enrico Grillini
 */
class DateUtilTest {

    @Test
    void formatTest() {
        LocalDateTime localDateTime = LocalDateTime.parse("2022-01-28T14:29:10.212", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        assertEquals("", DateUtil.format(null));
        assertEquals("28/01/2022", DateUtil.format(localDateTime));
        assertEquals("28/01/2022 14:29:10", DateUtil.format(localDateTime, DateUtil.DATE_TIME));
    }

    @Test
    void truncTest() {
        // LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse("2022-01-28T14:29:10.212", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime truncatedLocalDateTime = LocalDateTime.parse("2022-01-28T00:00:00.000", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        assertEquals(null, DateUtil.trunc(null, ChronoUnit.DAYS));
        assertEquals(truncatedLocalDateTime, DateUtil.trunc(localDateTime, ChronoUnit.DAYS));
    }

    @Test
    void fromOffSetDateTime() {
        TimeZone systemTimeZone = TimeZone.getDefault();
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Rome"));

        assertEquals(null, DateUtil.fromOffSetDateTime(null));

        LocalDateTime localDateTime = LocalDateTime.parse("2022-04-04T00:00:00.000", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2022-04-04T00:00:00.000+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        assertEquals(localDateTime, DateUtil.fromOffSetDateTime(offsetDateTime));

        offsetDateTime = OffsetDateTime.parse("2022-04-03T22:00:00.000+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        assertEquals(localDateTime, DateUtil.fromOffSetDateTime(offsetDateTime));

        // Ripristino il default
        TimeZone.setDefault(systemTimeZone);
    }

    @Test
    void toOffSetDateTime() {
        TimeZone systemTimeZone = TimeZone.getDefault();
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Rome"));

        assertEquals(null, DateUtil.toOffSetDateTime(null));

        LocalDateTime localDateTime = LocalDateTime.parse("2022-04-04T00:00:00.000", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2022-04-04T00:00:00.000+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        assertEquals(offsetDateTime, DateUtil.toOffSetDateTime(localDateTime));

        localDateTime = LocalDateTime.parse("2022-01-01T00:00:00.000", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        offsetDateTime = OffsetDateTime.parse("2022-01-01T00:00:00.000+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        assertEquals(offsetDateTime, DateUtil.toOffSetDateTime(localDateTime));

        // Ripristino il default
        TimeZone.setDefault(systemTimeZone);
    }

}
