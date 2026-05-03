package it.eg.sloth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import it.eg.sloth.spring.config.BaseObjectMapperConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.mock.web.MockHttpServletResponse;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TestUtil {

    public static final JsonMapper JSON_MAPPER = BaseObjectMapperConfig.defaultJsonMapper();
    public static final YAMLMapper YAML_MAPPER = BaseObjectMapperConfig.defaultYamlMapper();

    private TestUtil() {
        // NOP
    }

    public static <T> T readObject(String fileName, Class<T> valueType) throws JsonProcessingException {
        return BaseObjectMapperConfig.defaultJsonMapper().readValue(readFile(fileName), valueType);
    }

    public static String readFile(String fileName) {
        File file = new File(String.format("./src/test/resources/json/%s", fileName));

        return readFile(file);
    }


    public static String readFile(File readFile) {
        if (!readFile.isFile()) {
            Assertions.fail("File " + readFile + " non leggibile");
        }

        if (!readFile.exists()) {
            Assertions.fail("File " + readFile + " non trovato");
        }

        try {
            return FileUtils.readFileToString(readFile, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            Assertions.fail("File " + readFile + " non leggibile", ex);
            return null;
        }
    }

    public static void assertJsonEqualsFileToObj(String expectedFileName, Object object, String... ignoreFields) {
        assertJsonEqualsFileToStr(expectedFileName, BaseObjectMapperConfig.defaultJsonMapper().writeValueAsString(object), ignoreFields);
    }

    public static void assertJsonEqualsFileResponse(String expectedFileName, MockHttpServletResponse response, String... ignoreFields) {
        String actualStr = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
        assertJsonEqualsFileToStr(expectedFileName, actualStr, ignoreFields);
    }

    public static void assertJsonEqualsFileToStr(String expectedFileName, String actualStr, String... ignoreFields) {
        assertJsonEquals(expectedFileName, readFile(expectedFileName), actualStr, ignoreFields);
    }

    public static void assertJsonEquals(String fileName, String expectedStr, String actualStr, String... ignoreFields) {
        try {
            try {
                // "STRICT" pro fallimento test in presenza di campi aggiuntivi
                if (ignoreFields == null || ignoreFields.length == 0) {
                    JSONAssert.assertEquals(expectedStr, actualStr, JSONCompareMode.STRICT);
                } else {
                    Customization[] customizationsArray = new Customization[ignoreFields.length];
                    for (int i = 0; i < ignoreFields.length; i++) {
                        customizationsArray[i] = new Customization(ignoreFields[i], (o1, o2) -> true);
                    }
                    JSONAssert.assertEquals(expectedStr, actualStr, new CustomComparator(JSONCompareMode.STRICT, customizationsArray));
                }

            } catch (AssertionError ex) {
                FileUtils.writeStringToFile(new File(String.format("./target/actual/%s", fileName)), actualStr, StandardCharsets.UTF_8.name());
                Assertions.fail(ex);
            }
        } catch (JSONException | IOException ex) {
            log.error(ex.getMessage(), ex);
            Assertions.fail(ex);
        }
    }

    public static void   assertYamlEqualsFileToObj (String expectedFileName, Object object, String... ignoreFields) throws JsonProcessingException {
        assertYamlEqualsFileToStr(expectedFileName, BaseObjectMapperConfig.defaultYamlMapper().writeValueAsString(object), ignoreFields);
    }

    public static void assertYamlEqualsFileToStr(String expectedFileName, String actualStr, String... ignoreFields) {
        assertYamlEquals(expectedFileName, readFile(expectedFileName), actualStr, ignoreFields);
    }

    public static void assertYamlEqualsFile(String expectedFileName, String actualStr, String... ignoreFields) {
        assertYamlEquals(expectedFileName, readFile(expectedFileName), actualStr, ignoreFields);
    }

    public static void assertYamlEquals(String fileName, String expectedYamlStr, String actualYamlStr, String... ignoreFields) {
        try {
            String expectedJsonStr = JSON_MAPPER.writeValueAsString(YAML_MAPPER.readValue(expectedYamlStr, Object.class));
            String actualJsonStr = JSON_MAPPER.writeValueAsString(YAML_MAPPER.readValue(actualYamlStr, Object.class));

            try {
                // "STRICT" pro fallimento test in presenza di campi aggiuntivi
                if (ignoreFields == null || ignoreFields.length == 0) {
                    JSONAssert.assertEquals(expectedJsonStr, actualJsonStr, JSONCompareMode.STRICT);
                } else {
                    Customization[] customizationsArray = new Customization[ignoreFields.length];
                    for (int i = 0; i < ignoreFields.length; i++) {
                        customizationsArray[i] = new Customization(ignoreFields[i], (o1, o2) -> true);
                    }
                    JSONAssert.assertEquals(expectedJsonStr, actualJsonStr, new CustomComparator(JSONCompareMode.STRICT, customizationsArray));
                }

            } catch (AssertionError ex) {
                FileUtils.writeStringToFile(new File(String.format("./target/actual/%s", fileName)), actualYamlStr, StandardCharsets.UTF_8.name());
                Assertions.fail(ex);
            }
        } catch (JSONException | IOException ex) {
            log.error(ex.getMessage(), ex);
            Assertions.fail(ex);
        }
    }


}
