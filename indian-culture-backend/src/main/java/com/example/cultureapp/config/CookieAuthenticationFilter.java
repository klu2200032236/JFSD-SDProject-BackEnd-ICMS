//package com.example.cultureapp.config;
//
//import com.example.cultureapp.util.CookieUtil;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class CookieAuthenticationFilter extends OncePerRequestFilter {
//
//    private final CookieUtil cookieUtil;
//
//    public CookieAuthenticationFilter(CookieUtil cookieUtil) {
//        this.cookieUtil = cookieUtil;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String token = cookieUtil.getToken(request.getCookies()); // Retrieve token from cookies
//
//        if (token != null) {
//            try {
//                String username = Jwts.parser()
//                        .setSigningKey("secret")
//                        .parseClaimsJws(token)
//                        .getBody()
//                        .getSubject();
//
//                String role = Jwts.parser()
//                        .setSigningKey("secret")
//                        .parseClaimsJws(token)
//                        .getBody()
//                        .get("role", String.class);
//
//                if (username != null && role != null) {
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                            username, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
//                    SecurityContextHolder.getContext().setAuthentication(authentication); // Set authentication
//                }
//
//            } catch (SignatureException e) {
//                // Handle invalid token signature or token expired case
//            }
//        }
//
//        filterChain.doFilter(request, response); // Continue the filter chain
//    }
//}
