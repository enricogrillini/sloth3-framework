package it.eg.sloth.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import it.eg.sloth.core.token.JwtUtil;
import it.eg.sloth.core.token.TokenUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

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
class JwtUtilTest {

    private static final String PUBLIC_KEY = "classpath:public_key_jwt.pem";
    private static final String PRIVATE_KEY = "classpath:private_key_jwt.pem";

    @Test
    void jwtUtilTest() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, CertificateException {

        // iss (issuer): Issuer of the JWT
        String issuer = "issuer";

        // sub (subject): Subject of the JWT (the user)
        String subject = "subject";

        // aud (audience): Recipient for which the JWT is intended
        String audience = "audience";

        // exp (expiration time): Time after which the JWT expires
        long expirationTime = 3600 * 1000;

        // Custom claim
        Map<String, Object> claims = new HashMap<>();
        claims.put("prova", "prova-val");

        // Genero il JWT con la chiave privata
        PrivateKey privateKey = JwtUtil.getPrivateKey(PRIVATE_KEY);
        String jwt = JwtUtil.createJWT(issuer, subject, audience, expirationTime, claims, privateKey);
        assertNotNull(jwt);

        // Token Bearer
        String headerToken = TokenUtil.TOKEN_PREFIX + jwt;

        // Estraggo il token JWT dal Bearer token e lo valido
        PublicKey publicKey = JwtUtil.getPublicKey(PUBLIC_KEY);
        Jws<Claims> jws = JwtUtil.validateToken(TokenUtil.extractBearerToken(headerToken), publicKey);

        assertEquals(issuer, jws.getBody().getIssuer());
    }

    @Test
    void jwtUtilKoTest() {
        // extractJwtToken
        assertNull(TokenUtil.extractBearerToken((String) null));
        assertNull(TokenUtil.extractBearerToken("xxx"));

        // validateToken
        assertNull(JwtUtil.validateToken("", null));
    }
}
