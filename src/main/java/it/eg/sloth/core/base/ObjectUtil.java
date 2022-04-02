package it.eg.sloth.core.base;

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
            if (!isNull(object)) {
                return object;
            }
        }

        return null;
    }

    /**
     * Verifica se l'oggetto passato è null ("" e' considerato null)
     *
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        return object == null || object.toString().equals("");
    }

}
