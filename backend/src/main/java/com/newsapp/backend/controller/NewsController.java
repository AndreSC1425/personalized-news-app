package com.newsapp.backend.controller;

import com.newsapp.backend.service.NewsApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news") // Frontend will call this base path
public class NewsController {

    private final NewsApiService newsApiService;

    public NewsController(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchNewsByTopics(@RequestParam String topics) {
        try {
            // Call the backend service to get news from NewsAPI
            String newsJson = newsApiService.getNewsFromExternalApi(topics);
            return ResponseEntity.ok(newsJson);
        } catch (RuntimeException e) {
            System.err.println("Error in NewsController: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Failed to retrieve news: " + e.getMessage() + "\"}");
        }
    }
}