package it.eg.sloth.api.common;

import it.eg.sloth.core.base.DateUtil;
import it.eg.sloth.core.base.ObjectUtil;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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
public class PojoMapper {

    private static final String TRUE_VALUE = "S";
    private static final String FALSE_VALUE = "N";

    public OffsetDateTime toOffSetDateTime(LocalDateTime value) {
        return DateUtil.toOffSetDateTime(value);
    }

    public LocalDateTime fromOffSetDateTime(OffsetDateTime value) {
        return DateUtil.fromOffSetDateTime(value);
    }

    public String toString(Boolean value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        } else {
            return Boolean.TRUE.equals(value) ? TRUE_VALUE : FALSE_VALUE;
        }
    }

    public Boolean fromString(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        } else {
            return TRUE_VALUE.equals(value);
        }
    }

}