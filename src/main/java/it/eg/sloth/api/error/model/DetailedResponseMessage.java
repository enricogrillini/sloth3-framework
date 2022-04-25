package it.eg.sloth.api.error.model;

import io.swagger.v3.oas.annotations.media.Schema;
import it.eg.sloth.core.base.StringUtil;
import lombok.Data;

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
public class DetailedResponseMessage<T> extends ResponseMessage {

    @Schema(description = "Dettaglio risposta")
    T detail;


    public DetailedResponseMessage() {
        this(StringUtil.EMPTY, ResponseCode.OK, null);
    }

    public DetailedResponseMessage(String description, T detail) {
        this(description, ResponseCode.OK, detail);
    }

    public DetailedResponseMessage(String description, ResponseCode code, T detail) {
        super(description, code);
        this.detail = detail;
    }

}
