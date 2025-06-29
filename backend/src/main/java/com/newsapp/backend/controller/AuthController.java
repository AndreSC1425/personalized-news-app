package com.newsapp.backend.controller;

import com.newsapp.backend.dto.JwtResponse;
import com.newsapp.backend.dto.LoginRequest;
import com.newsapp.backend.dto.RegisterRequest;
import com.newsapp.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://personalized-news-ej34qck3b-andresc1425s-projects.vercel.app", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        JwtResponse jwt = authService.login(request);
        return ResponseEntity.ok(jwt);
    }

}
