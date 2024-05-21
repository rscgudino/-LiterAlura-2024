package com.example.literalura;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class BookApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "https://gutendex.com/books";

    public String fetchBooks() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl);
        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }



}
