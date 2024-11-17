package com.ksh.budgetmanagement.utils;

import com.ksh.budgetmanagement.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private User user;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "SECRET_KEY", "testSecretKeytestSecretKeytestSecretKeytestSecretKeytestSecretKey");
        ReflectionTestUtils.setField(jwtUtil, "ISSUER", "testIssuer");
        ReflectionTestUtils.setField(jwtUtil, "EXPIRATION_TIME", 3600000L); // 1 hour

        user = User.builder()
                .id(1L)
                .email("test@example.com")
                .build();
    }

    @Test
    void testCreateToken() {
        String token = jwtUtil.createToken(user);
        assertNotNull(token);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey("testSecretKeytestSecretKeytestSecretKeytestSecretKeytestSecretKey")
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals("testIssuer", claims.getIssuer());
        assertEquals("test@example.com", claims.getSubject());
        assertEquals(1L, claims.get("id", Long.class));
        assertEquals("test@example.com", claims.get("email", String.class));
    }

    @Test
    void testValidateToken() {
        String token = jwtUtil.createToken(user);
        assertTrue(jwtUtil.validateToken(token));

        String invalidToken = token + "invalid";
        assertFalse(jwtUtil.validateToken(invalidToken));
    }

    @Test
    void testGetUserIdFromToken() {
        String token = jwtUtil.createToken(user);
        Long userId = jwtUtil.getUserIdFromToken(token);
        assertEquals(1L, userId);
    }

    @Test
    void testGetEmailFromToken() {
        String token = jwtUtil.createToken(user);
        String email = jwtUtil.getEmailFromToken(token);
        assertEquals("test@example.com", email);
    }
}