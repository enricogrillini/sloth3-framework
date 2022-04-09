package it.eg.sloth.api.error.model;

import io.swagger.v3.oas.annotations.media.Schema;
import it.eg.sloth.api.error.exception.FrameException;
import it.eg.sloth.core.base.StringUtil;
import lombok.Data;
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
@Data
public class ResponseMessage {

    public boolean isSuccess() {
        return getCode().getHttpStatus() == HttpStatus.OK;
    }

    @Schema(description = "Codice risposta", required = true, example = "TIMEOUT")
    private ResponseCode code;

    @Schema(description = "Messaggio di risposta", required = true, example = "Ok")
    public String getMessage() {
        return getCode().getMessage();
    }

    @Schema(description = "Descrizione dettagliata", required = true, example = "Documento inserito corerttamente")
    private String description;

    public ResponseMessage() {
        this(StringUtil.EMPTY, ResponseCode.OK);
    }

    public ResponseMessage(String description) {
        this(description, ResponseCode.OK);
    }

    public ResponseMessage(String description, ResponseCode code) {
        this.description = description;
        this.code = code;
    }

    public ResponseMessage(Throwable throwable) {
        this.code = ResponseCode.SYSTEM_ERROR;
        this.description = throwable.toString();
    }

    public ResponseMessage(FrameException exception) {
        this.code = exception.getCode();
        this.description = exception.getMessage();
    }

}
