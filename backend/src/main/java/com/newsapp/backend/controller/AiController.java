package com.newsapp.backend.controller;

import com.newsapp.backend.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/summarize")
    public ResponseEntity<String> summarize(@RequestBody String articleText) {
        String summary = aiService.summarizeText(articleText);
        return ResponseEntity.ok(summary);
    }
}
