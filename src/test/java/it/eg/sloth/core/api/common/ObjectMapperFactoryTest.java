package it.eg.sloth.core.api.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.eg.sloth.api.common.ObjectMapperFactory;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMapperFactoryTest {

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
