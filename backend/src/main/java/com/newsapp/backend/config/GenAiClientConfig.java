package com.newsapp.backend.config;

import com.google.genai.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GenAiClientConfig {
    @Bean
    @Profile("developer-api")
    public Client geminiDeveloperClient(
            @Value("${app.gemini.api-key}") String apiKey) {
        return Client.builder().apiKey(apiKey).build();
    }
}