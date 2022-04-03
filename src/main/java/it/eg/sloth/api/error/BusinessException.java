package it.eg.sloth.api.error;

import lombok.Getter;

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
@Getter
public class BusinessException extends RuntimeException {

    private final ResponseCode responseCode;

    public BusinessException(ResponseCode businessErrorCode) {
        super();
        this.responseCode = businessErrorCode;
    }


    public BusinessException(Throwable cause) {
        this(cause, ResponseCode.GENERIC);
    }

    public BusinessException(Throwable cause, ResponseCode businessErrorCode) {
        super(cause.getMessage(), cause);
        this.responseCode = businessErrorCode;
    }

}
