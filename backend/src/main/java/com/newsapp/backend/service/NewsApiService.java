package com.newsapp.backend.service;

import com.newsapp.backend.config.NewsApiConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NewsApiService {

    private final RestClient restClient;
    private final NewsApiConfig newsApiConfig;

    // Inject NewsApiConfig and RestClient.Builder
    public NewsApiService(RestClient.Builder restClientBuilder, NewsApiConfig newsApiConfig) {
        this.restClient = restClientBuilder.build();
        this.newsApiConfig = newsApiConfig;
    }

    /**
     * Fetches news from gnews.io.
     * This method is called by NewsController.
     */
    public String getNewsFromExternalApi(String query) {
        // Construct the URL for Gnews API
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(newsApiConfig.getBaseUrl() + "/search")
                .queryParam("q", query)
                .queryParam("lang", "en") // GNews uses 'lang' not 'language'
                .queryParam("token", newsApiConfig.getApiKey());

        String newsApiUrl = uriBuilder.toUriString();

        try {
            // Use RestClient to make the HTTP call
            ResponseEntity<String> response = restClient.get()
                    .uri(newsApiUrl)
                    .retrieve()
                    .toEntity(String.class); // Get the JSON string
            System.err.println(response.getBody());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            } else {
                System.err.println("NewsAPI returned non-successful status: " + response.getStatusCode() + " Body: " + response.getBody());
                throw new RuntimeException("Failed to fetch news from GNews API with status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Error calling GNews API: " + e.getMessage());
            throw new RuntimeException("Error communicating with GNews API.", e);
        }
    }
}