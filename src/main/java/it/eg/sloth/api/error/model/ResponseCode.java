package it.eg.sloth.api.error.model;

import org.springframework.http.HttpStatus;

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
public enum ResponseCode {

    OK("Ok", HttpStatus.OK),
    NOT_FOUND("Non trovato", HttpStatus.NOT_FOUND),
    BUSINESS_ERROR("Errore generico", HttpStatus.BAD_REQUEST),
    SYSTEM_ERROR("Errore di sistema", HttpStatus.INTERNAL_SERVER_ERROR);

    private String message;
    private HttpStatus httpStatus;

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    ResponseCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
