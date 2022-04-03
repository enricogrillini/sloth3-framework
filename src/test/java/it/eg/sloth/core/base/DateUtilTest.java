package it.eg.sloth.core.base;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Project: sloth-framework
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

        assertEquals(truncatedLocalDateTime, DateUtil.trunc(localDateTime, ChronoUnit.DAYS));
    }

}
