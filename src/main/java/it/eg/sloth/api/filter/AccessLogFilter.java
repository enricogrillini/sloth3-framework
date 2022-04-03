package it.eg.sloth.api.filter;

import it.eg.sloth.core.base.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

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
@Slf4j
public class AccessLogFilter implements Filter {

    public static final String REQUEST_ID = "X-Request-ID";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Instant start = Instant.now();

        String requestId = req.getHeader(REQUEST_ID);
        if (ObjectUtil.isNull(requestId)) {
            requestId = generateRequestId();
        }

        resp.setHeader(REQUEST_ID, requestId);
        try (MDC.MDCCloseable m = MDC.putCloseable(REQUEST_ID, requestId)) {
            // Access Log IN
            log.info("IN  - {} - {}, protocol {}, host: {}", req.getMethod(), req.getRequestURI(), req.getProtocol(), req.getRemoteHost());

            chain.doFilter(request, response);

            // Access Log OUT
            log.info("OUT - {} - {}, status {}, duration {}", req.getMethod(), req.getRequestURI(), resp.getStatus(), ChronoUnit.MILLIS.between(start, Instant.now()));
        }
    }

    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }

}
