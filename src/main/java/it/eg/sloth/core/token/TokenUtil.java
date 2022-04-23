package it.eg.sloth.core.token;

import it.eg.sloth.core.base.ObjectUtil;
import it.eg.sloth.core.base.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

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
public class TokenUtil {

    private TokenUtil() {
        // NOP
    }

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";


    public static String extractBearerToken(HttpServletRequest request) {
        return extractBearerToken(request.getHeader(TOKEN_HEADER));
    }

    public static String extractBearerToken(String headerToken) {
        if (!ObjectUtil.isEmpty(headerToken) && headerToken.startsWith(TOKEN_PREFIX)) {
            return headerToken.replace(TOKEN_PREFIX, StringUtil.EMPTY);
        } else {
            return null;
        }
    }

}
