package it.eg.sloth.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.util.TimeZone;

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
public class ObjectMapperFactory {
    private ObjectMapperFactory() {
        // NOP
    }

    public static ObjectMapper objectMapper() {
        return objectMapper(TimeZone.getDefault());
    }

    public static ObjectMapper objectMapper(TimeZone timeZone) {
        return JsonMapper
                .builder()
                .addModules(new Jdk8Module(), new JavaTimeModule(), new ParameterNamesModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .serializationInclusion(JsonInclude.Include.NON_ABSENT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .defaultDateFormat(new StdDateFormat())
                .defaultTimeZone(timeZone)
                .build();
    }


}