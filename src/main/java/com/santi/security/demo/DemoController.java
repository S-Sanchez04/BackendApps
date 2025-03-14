package com.santi.security.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/demo-controller")
    public ResponseEntity<Map<String, String>> getDemo() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Estamos dentro papaaaa");
        return ResponseEntity.ok(response);
    }
}