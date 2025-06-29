package com.newsapp.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewsApiConfig {

    @Value("${newsapi.base-url:https://newsapi.org/v2}")
    private String baseUrl;

    @Value("${newsapi.api-key: 5e1d4fd9df264397bedf4b8cbeb5a73b}")
    private String apiKey;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }
}