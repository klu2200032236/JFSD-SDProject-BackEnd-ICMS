//
//package com.example.cultureapp.controller;
//
//import com.example.cultureapp.model.User;
//import com.example.cultureapp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:3000")
//public class AuthController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User loginRequest) {
//        // Authenticate the user
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication); // Store authentication in context
//        
//        User user = userService.findByUsername(loginRequest.getUsername());
//
//        if (user != null) {
//            return ResponseEntity.ok("{\"message\":\"Login successful\", \"role\":\"" + user.getRole() + "\"}");
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body("{\"message\":\"Invalid username or password\"}");
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout() {
//        // Clear authentication and invalidate session
//        SecurityContextHolder.clearContext();
//        return ResponseEntity.ok("{\"message\":\"Logout successful\"}");
//    }
//}

















package com.example.cultureapp.controller;

import com.example.cultureapp.model.User;
import com.example.cultureapp.service.UserService;
import com.example.cultureapp.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseCookie;

import java.util.Date;
import javax.crypto.SecretKey;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CookieUtil cookieUtil;

    // Generate a secure secret key for HS512
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\":\"Username already exists\"}");
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\":\"Role is required\"}");
        }

        userService.registerUser(user);
        return ResponseEntity.ok("{\"message\":\"User registered successfully\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());

        if (user != null && userService.matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            // Generate JWT token with the secure key
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("role", user.getRole())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiry
                    .signWith(SECRET_KEY)
                    .compact();

            // Create a cookie for the token
            ResponseCookie cookie = cookieUtil.createCookie(token);

            return ResponseEntity.ok()
                    .header("Set-Cookie", cookie.toString())
                    .body("{\"message\":\"Login successful\", \"role\":\"" + user.getRole() + "\"}");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("{\"message\":\"Invalid username or password\"}");
    }
}









//
//
//package com.example.cultureapp.controller;
//
//
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.cultureapp.model.User;
//import com.example.cultureapp.service.UserService;
//
//import java.util.Date;
//
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:3000")
//public class AuthController {
//
//    private static final String SECRET_KEY = "your_secret_key";
//    private static final long EXPIRATION_TIME = 86400000; // 1 day
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User loginRequest) {
//        User user = userService.findByUsername(loginRequest.getUsername());
//        if (user != null && userService.matchesPassword(loginRequest.getPassword(), user.getPassword())) {
//            // Generate JWT
//            String token = Jwts.builder()
//                    .setSubject(user.getUsername())
//                    .claim("role", user.getRole())
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
//                    .compact();
//
//            return ResponseEntity.ok("{\"message\":\"Login successful\", \"token\":\"" + token + "\", \"role\":\"" + user.getRole() + "\"}");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\":\"Invalid username or password\"}");
//    }
//}



















