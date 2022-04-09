package it.eg.sloth.api.error;

import it.eg.sloth.api.error.exception.BusinessException;
import it.eg.sloth.api.error.exception.SystemException;
import it.eg.sloth.api.error.model.ResponseCode;
import it.eg.sloth.api.error.model.ResponseMessage;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

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
class ErrorTest {

    @Test
    void errorTest() {
        BusinessException businessException = new BusinessException("Prova", ResponseCode.BUSINESS_ERROR);

        ResponseMessage responseMessage = new ResponseMessage(businessException);
        assertFalse(responseMessage.isSuccess());
        assertEquals(ResponseCode.BUSINESS_ERROR, responseMessage.getCode());
        assertEquals(ResponseCode.BUSINESS_ERROR.getMessage(), responseMessage.getMessage());
        assertEquals("Prova", responseMessage.getDescription());

        try {
            throw new SQLException("Prova");
        } catch (SQLException e) {
            responseMessage = new ResponseMessage(new SystemException(e));
            assertFalse(responseMessage.isSuccess());
            assertEquals(ResponseCode.SYSTEM_ERROR, responseMessage.getCode());
            assertEquals(ResponseCode.SYSTEM_ERROR.getMessage(), responseMessage.getMessage());
            assertEquals("java.sql.SQLException: Prova", responseMessage.getDescription());

            responseMessage = new ResponseMessage(new BusinessException(e));
            assertFalse(responseMessage.isSuccess());
            assertEquals(ResponseCode.BUSINESS_ERROR, responseMessage.getCode());
            assertEquals(ResponseCode.BUSINESS_ERROR.getMessage(), responseMessage.getMessage());
            assertEquals("java.sql.SQLException: Prova", responseMessage.getDescription());
        }
    }

    @Test
    void messageTest() {
        ResponseMessage responseMessage = new ResponseMessage();
        assertTrue(responseMessage.isSuccess());
        assertEquals(ResponseCode.OK, responseMessage.getCode());
        assertEquals(ResponseCode.OK.getMessage(), responseMessage.getMessage());
        assertEquals("", responseMessage.getDescription());

        responseMessage = new ResponseMessage("Prova");
        assertTrue(responseMessage.isSuccess());
        assertEquals(ResponseCode.OK, responseMessage.getCode());
        assertEquals(ResponseCode.OK.getMessage(), responseMessage.getMessage());
        assertEquals("Prova", responseMessage.getDescription());

        responseMessage = new ResponseMessage(new SQLException("Prova"));
        assertFalse(responseMessage.isSuccess());
        assertEquals(ResponseCode.SYSTEM_ERROR, responseMessage.getCode());
        assertEquals(ResponseCode.SYSTEM_ERROR.getMessage(), responseMessage.getMessage());
        assertEquals("java.sql.SQLException: Prova", responseMessage.getDescription());
    }
}
