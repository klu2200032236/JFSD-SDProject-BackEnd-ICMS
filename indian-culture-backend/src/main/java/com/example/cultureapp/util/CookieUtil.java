package com.example.cultureapp.util;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class CookieUtil {

    public static final String COOKIE_NAME = "auth_token";

    // Create a cookie with the token
    public ResponseCookie createCookie(String token) {
        return ResponseCookie.from(COOKIE_NAME, token)
                .httpOnly(true) // Ensure the cookie is not accessible via JS
                .secure(true)   // Set secure to true for production
                .path("/")
                .maxAge(24 * 60 * 60) // Expire after 1 day
                .build();
    }

    // Get token from cookie
    public String getToken(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
