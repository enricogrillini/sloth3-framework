package it.eg.sloth.util;

import org.apache.commons.lang3.StringUtils;

public class PropertyUtil {

    // Estrae il type da un codProperty
    public static String getType(String codProperty) {
        if (StringUtils.isEmpty(codProperty)) {
            return "";
        }

        return StringUtils.substring(codProperty, 0, codProperty.indexOf(":"));
    }

    // Estrae il value da un codProperty
    public static String getValue(String codProperty) {
        if (StringUtils.isEmpty(codProperty)) {
            return "";
        }

        return StringUtils.substring(codProperty, codProperty.indexOf(":") + 1);
    }
}
