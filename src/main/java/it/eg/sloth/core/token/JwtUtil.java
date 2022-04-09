package it.eg.sloth.core.token;

import io.jsonwebtoken.*;
import it.eg.sloth.core.base.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.Map;

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
public class JwtUtil {
    private JwtUtil() {
        // NOP
    }

    public static PublicKey getPublicKey(String certificatePath) throws IOException, CertificateException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        try (InputStream inputStream = resourceLoader.getResource(certificatePath).getInputStream()) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(inputStream);
            return cert.getPublicKey();
        }
    }

    public static PrivateKey getPrivateKey(String certificatePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        try (InputStream inputStream = resourceLoader.getResource(certificatePath).getInputStream()) {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String pem = new String(bdata, StandardCharsets.UTF_8);
            pem = pem.replace("-----BEGIN PRIVATE KEY-----", "");
            pem = pem.replace("-----END PRIVATE KEY-----", "");

            return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(new Base64().decode(pem)));
        }
    }

    public static String createJWT(String issuer, String subject, String audience, long ttlMillis, Map<String, Object> claims, PrivateKey privateKey) {

        //The JWT signature algorithm we will be using to sign the token
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuer(issuer)
                .setAudience(audience)
                .addClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.RS256, privateKey);

        // if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Jws<Claims> validateToken(String token, PublicKey publicKey) {
        if (!ObjectUtil.isNull(token)) {
            try {
                return Jwts.parser()
                        .setSigningKey(publicKey)
                        .parseClaimsJws(token);

            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
            } catch (SignatureException exception) {
                log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
            }
        }

        return null;
    }

}
