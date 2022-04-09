package it.eg.sloth.api.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.eg.sloth.api.common.ObjectMapperFactory;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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
class ObjectMapperFactoryTest {

    @Test
    void serializeDeserializeTest() throws JsonProcessingException {
        MapperPojo pojo = new MapperPojo();
        pojo.setId(10);
        pojo.setNumber(123.45);
        pojo.setTesto("prova");
        pojo.setLocalDateTime(LocalDateTime.now());
        pojo.setOffsetDateTime(OffsetDateTime.now());
        pojo.setFlag(true);

        ObjectMapper objectMapper = ObjectMapperFactory.objectMapper();

        // Serialize
        String serializedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);

        // Deserialize
        MapperPojo deserializedPojo = objectMapper.readValue(serializedJson, MapperPojo.class);

        assertEquals(pojo, deserializedPojo);
    }

    @Data
    public static class MapperPojo {
        private Integer id;
        private Double number;
        private String testo;
        private LocalDateTime localDateTime;
        private OffsetDateTime offsetDateTime;
        private Boolean flag;
    }
}
