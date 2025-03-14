package com.santi.security.cat;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.Map;

@Service
public class CatService {
    private final WebClient webClient;

    public CatService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.thecatapi.com/v1").build();
    }

    public Mono<String> getRandomCatImage() {
        return webClient.get()
                .uri("/images/search")
                .retrieve()
                .bodyToMono(Map[].class)
                .map(response -> response[0].get("url").toString());
    }
}