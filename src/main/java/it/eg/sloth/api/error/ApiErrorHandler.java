package it.eg.sloth.api.error;

import it.eg.sloth.api.error.exception.BusinessException;
import it.eg.sloth.api.error.exception.SystemException;
import it.eg.sloth.api.error.model.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
@Slf4j
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    private static final String EXCEPTION_MESSAGE_TRAILER = "An exception occurred: {}";

    /**
     * Business exception
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ResponseMessage> handleBusinessException(final BusinessException ex, final WebRequest request) {
        ResponseMessage responseMessage = new ResponseMessage(ex);
        log.error(EXCEPTION_MESSAGE_TRAILER, responseMessage);

        return new ResponseEntity<>(responseMessage, responseMessage.getCode().getHttpStatus());
    }

    /**
     * System exception
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({SystemException.class})
    public ResponseEntity<ResponseMessage> handleSystemException(final SystemException ex, final WebRequest request) {
        ResponseMessage responseMessage = new ResponseMessage(ex);
        log.error(EXCEPTION_MESSAGE_TRAILER, responseMessage, ex);

        return new ResponseEntity<>(responseMessage, responseMessage.getCode().getHttpStatus());
    }

    /**
     * Throwable
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<ResponseMessage> handleGenericException(final Throwable ex, final WebRequest request) {
        return handleSystemException(new SystemException(ex), request);
    }

}