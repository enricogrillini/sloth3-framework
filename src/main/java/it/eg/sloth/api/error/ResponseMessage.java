package it.eg.sloth.api.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: sloth-framework
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
@NoArgsConstructor
public class ResponseMessage {

    @Schema(description = "Esito risposta", required = true, example = "true")
    private Boolean success;

    @Schema(description = "Codice", required = true, example = "TIMEOUT")
    private String code;

    @Schema(description = "Messaggio di risposta", required = true, example = "Documento inserito corerttamente")
    private String message;

    @Schema(description = "Descrizione", required = true, example = "Documento inserito corerttamente")
    private String description;

    public ResponseMessage(Boolean success, ResponseCode responseCode, String description) {
        this.success = success;
        this.code = responseCode.name();
        this.message = responseCode.getMessage();
        this.description = description;
    }

    public ResponseMessage(Exception exception) {
        this.success = false;
        this.code = null;
        this.message = exception.getMessage();
        this.description = null;
    }

    public ResponseMessage(BusinessException exception) {
        this.success = false;
        this.code = exception.getResponseCode().name();
        this.message = exception.getResponseCode().getMessage();
        this.description = exception.getCause() == null ? null : exception.getCause().getMessage();
    }

}
