package com.newsapp.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewsApiConfig {

    @Value("${gnews.base-url:https://gnews.io/api/v4}")
    private String baseUrl;

    @Value("${gnews.api-key: 4f86e751682dbf48ce7593e7a046e862}")
    private String apiKey;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }
}