package it.eg.sloth.core.base;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

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
public class DateUtil {

    private DateUtil() {
        // NOP
    }

    public static final String DATE = "dd/MM/yyyy";
    public static final String DATE_TIME = "dd/MM/yyyy HH:mm:ss";

    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, DATE);
    }

    public static String format(LocalDateTime localDateTime, String pattern) {
        if (ObjectUtil.isNull(localDateTime)) {
            return StringUtil.EMPTY;
        } else {
            return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        }
    }

    public static LocalDateTime trunc(LocalDateTime localDateTime, TemporalUnit temporalUnit) {
        if (ObjectUtil.isNull(localDateTime)) {
            return null;
        } else {
            return localDateTime.truncatedTo(temporalUnit);
        }
    }

    public static LocalDateTime fromOffSetDateTime(OffsetDateTime offsetDateTime) {
        if (ObjectUtil.isNull(offsetDateTime)) {
            return null;
        } else {
            return offsetDateTime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

    public static OffsetDateTime toOffSetDateTime(LocalDateTime localDateTime) {
        if (ObjectUtil.isNull(localDateTime)) {
            return null;
        } else {
            return localDateTime.atOffset(ZoneId.systemDefault().getRules().getOffset(localDateTime));
        }
    }
}
