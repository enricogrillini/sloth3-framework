package it.eg.sloth.core.base;

import java.util.Collection;

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
public class ObjectUtil {

    private ObjectUtil() {
        // NOP
    }

    /**
     * Ritorna il primo oggetto non nullo
     *
     * @param objects
     * @return
     */
    public static Object coalesce(Object... objects) {
        for (Object object : objects) {
            if (!isEmpty(object)) {
                return object;
            }
        }

        return null;
    }

    /**
     * Verifica se l'oggetto passato è vuoto ("" e' considerato null)
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        } else if (object instanceof String) {
            return StringUtil.EMPTY.equals(object);
        } else {
            return false;
        }
    }

}
