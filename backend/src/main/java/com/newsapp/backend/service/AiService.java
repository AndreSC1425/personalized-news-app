package com.newsapp.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Content;
import com.google.genai.types.Part;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;

import java.util.*;

@Service
public class AiService {

    private final Client genaiClient;

    @Value("${app.vertex.ai.model:gemini-2.5-flash}")
    private String modelName;

    public AiService(Client genaiClient) {
        this.genaiClient = genaiClient;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    public String summarizeText(String text) {
        Content content = Content.builder()
                .parts(Collections.singletonList(
                        Part.builder().text("Summarize the following news article concisely, focusing on key facts and main points, in about 3-5 sentences:\n\n" + text).build()
                ))
                .build();
        GenerateContentConfig config = GenerateContentConfig.builder().build();
        try {
            GenerateContentResponse response = genaiClient.models.generateContent(modelName, content, config);

            return response.text();

        } catch (Exception e) {
            System.err.println("Error calling Google GenAI API for summarization: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to generate summary from AI. " + e.getMessage(), e);
        }
    }
}
