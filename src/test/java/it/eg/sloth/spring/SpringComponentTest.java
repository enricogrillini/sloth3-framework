package it.eg.sloth.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.eg.sloth.core.token.JwtUtil;
import it.eg.sloth.core.token.TokenUtil;
import it.eg.sloth.spring.context.DummyApplication;
import it.eg.sloth.spring.context.controller.DummyController;
import it.eg.sloth.spring.context.config.SecurityConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

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
@SpringBootTest(classes = {DummyApplication.class, DummyController.class, SecurityConfig.class})
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringComponentTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final String URI_GET = "/api/v1/dummy/get";
    private static final String URI_SYSTEM_EXCEPTION = "/api/v1/dummy/systemException";
    private static final String URI_BUSINESS_EXCEPTION = "/api/v1/dummy/businessException";

    private static PrivateKey privateKey;

    @BeforeAll
    public static void beforeAll() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        privateKey = JwtUtil.getPrivateKey("private_key_jwt.pem");
    }

    @Test
    @Order(1)
    void getTest() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(URI_GET)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        // Verifico lo stato della risposta
        assertEquals(HttpStatus.FORBIDDEN.value(), mvcResult.getResponse().getStatus());

        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(URI_GET)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .header(TokenUtil.TOKEN_HEADER, TokenUtil.TOKEN_PREFIX + "prova"))
                .andReturn();

        // Verifico lo stato della risposta
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(2)
    void errorHandlerTest() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(URI_SYSTEM_EXCEPTION)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .header(TokenUtil.TOKEN_HEADER, TokenUtil.TOKEN_PREFIX + "prova"))
                .andReturn();

        // Verifico lo stato della risposta
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());

        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(URI_BUSINESS_EXCEPTION)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .header(TokenUtil.TOKEN_HEADER, TokenUtil.TOKEN_PREFIX + "prova"))
                .andReturn();

        // Verifico lo stato della risposta
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

}