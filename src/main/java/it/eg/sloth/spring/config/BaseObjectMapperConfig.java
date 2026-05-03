package it.eg.sloth.spring.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.util.TimeZone;

public interface BaseObjectMapperConfig {
    @Bean
    default JsonMapper jsonMapper() {
        return defaultJsonMapper();
    }

    @Bean
    default YAMLMapper yamlMapper() {
        return defaultYamlMapper();
    }

    static JsonMapper defaultJsonMapper() {
        return ((JsonMapper.Builder)((JsonMapper.Builder)((JsonMapper.Builder)((JsonMapper.Builder)((JsonMapper.Builder)JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)).configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false)).configure(SerializationFeature.INDENT_OUTPUT, true)).changeDefaultPropertyInclusion((incl) -> {
            return incl.withValueInclusion(Include.NON_NULL);
        })).defaultTimeZone(TimeZone.getDefault())).build();
    }

    static YAMLMapper defaultYamlMapper() {
        return (YAMLMapper)((YAMLMapper.Builder)((YAMLMapper.Builder)YAMLMapper.builder().configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)).configure(com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false)).build();
    }
}