package com.santi.security.cat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cats")
public class CatApiController {
    private final CatService catService;

    public CatApiController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/random")
    public Mono<String> getRandomCatImage() {
        return catService.getRandomCatImage();
    }

}