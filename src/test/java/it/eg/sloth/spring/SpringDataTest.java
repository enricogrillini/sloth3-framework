package it.eg.sloth.spring;

import it.eg.sloth.spring.context.DummyApplication;
import it.eg.sloth.spring.context.config.SecurityConfig;
import it.eg.sloth.spring.context.controller.DummyController;
import it.eg.sloth.spring.context.model.Document;
import it.eg.sloth.spring.context.repository.DocumentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
class SpringDataTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DocumentRepository documentRepository;

    @Test
    @Order(1)
    void initTest() {
        documentRepository.insertError(1, false);
        documentRepository.insertError(2, false);
        documentRepository.insertError(3, false);
    }

    @Test
    @Order(2)
    void selectFilteredQueryTest() {
        List<Document> list = documentRepository.selectFilteredQuery(null);
        assertEquals(3, list.size());

        list = documentRepository.selectFilteredQuery(1);
        assertEquals(1, list.size());
    }

    @Test
    @Order(3)
    void selectQueryTest() {
        List<Document> list = documentRepository.selectQuery(1);
        assertEquals(1, list.size());
    }

//    @Test
//    void insertDocument() {
//
//
//        Query query = new Query(jdbcTemplate, "select * from DOCUMENT");
//        List<Document> list = query.selectTable(Document.class);
//
//        query.setStatement("select * from DOCUMENT where idDocument = ?");
//        query.addParameter(1);
//        list = query.selectTable(Document.class);
//
//        Document aaa =    query.selectRow(Document.class);
//
//        try {
//            documentRepository.insertError(4, true);
//        } catch (Exception a) {
//
//        }
//
//        list = documentRepository.select(null);
//
//        System.out.println(list);
//    }


}