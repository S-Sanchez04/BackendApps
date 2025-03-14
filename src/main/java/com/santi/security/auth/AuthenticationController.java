package com.santi.security.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AuthenticationResponse response = service.register(request);
            return ResponseEntity.ok(response);
        } catch (EmailAlreadyExistsException ex) {
            // Crear un objeto de respuesta de error con información útil
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Email already exists");
            errorResponse.put("message", ex.getMessage());
            // Devolver un 409 Conflict en lugar de 403 Forbidden
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Registration failed");
            errorResponse.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @RequestMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}


