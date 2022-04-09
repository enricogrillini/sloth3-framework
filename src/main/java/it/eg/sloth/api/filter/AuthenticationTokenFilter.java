package it.eg.sloth.api.filter;

import it.eg.sloth.core.base.ObjectUtil;
import it.eg.sloth.core.token.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
public class AuthenticationTokenFilter extends BasicAuthenticationFilter {

    Set<String> tokens;

    public AuthenticationTokenFilter(AuthenticationManager authManager, String... tokens) {
        super(authManager);
        this.tokens = new HashSet<>(Arrays.asList(tokens));
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = TokenUtil.extractBearerToken(request);
        if (!ObjectUtil.isNull(token) && tokens.contains(token)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(token, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

}
