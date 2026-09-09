package com.coinpurse.web.infrastructure.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map;

@Component
public class CurrencyApiClient {
    private static final Logger log = LoggerFactory.getLogger(CurrencyApiClient.class);

    private final WebClient webClient;
    private final String apiVersion;
    private final String endpointAllCurrencies;

    public CurrencyApiClient(
            @Value("${currency.service.base-url}") String baseUrl,
            @Value("${currency.service.api-version:v1}") String apiVersion,
            @Value("${currency.service.endpoints.all-currencies}") String endpointAllCurrencies) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
        this.apiVersion = apiVersion;
        this.endpointAllCurrencies = endpointAllCurrencies;
    }

    // Refactor to using Mono for a reactive / async approach to data fetch
    /**
     * Fetches currencies updates
     * @param localDate
     * @return a promise to a Map of String, String containing currencies
     */
    public Mono<Map<String, String>> fetchCurrenciesByDate(LocalDate localDate) {
        String formattedDate = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/{apiVersion}/{endpoint}")
                        .queryParam("date", formattedDate)
                        .build(apiVersion, endpointAllCurrencies))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                // Use ParameterizedTypeReference for de-serialisation. Type erasure makes this a necessity
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
                .onErrorResume(WebClientResponseException.class, ex -> {
                    log.error("HTTP Error fetching currencies for date {}: {} - {}", formattedDate, ex.getStatusCode(), ex.getResponseBodyAsString());
                    return Mono.just(Collections.emptyMap());
                })
                .onErrorResume(ex -> {
                    log.error("Unexpected error fetching currencies for date {}: {}", formattedDate, ex.getMessage(), ex);
                    return Mono.just(Collections.emptyMap());
                });
    }
}