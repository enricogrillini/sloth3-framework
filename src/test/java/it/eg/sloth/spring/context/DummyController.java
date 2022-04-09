package it.eg.sloth.spring.context;

import it.eg.sloth.api.error.exception.BusinessException;
import it.eg.sloth.api.error.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/api/v1/dummy")
@Slf4j
public class DummyController {


    @GetMapping(path = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get() {
        return "prova";
    }

    @GetMapping(path = "systemException", produces = MediaType.APPLICATION_JSON_VALUE)
    public String systemException() {
        throw new RuntimeException("Prova");
    }

    @GetMapping(path = "businessException", produces = MediaType.APPLICATION_JSON_VALUE)
    public String businessException() {
        throw new BusinessException("Prova");
    }


}




