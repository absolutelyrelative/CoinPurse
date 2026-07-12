package com.coinpurse.web.services.impl;

import com.coinpurse.web.dto.currency.CurrencyDto;
import com.coinpurse.web.services.CurrencyServices;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.coinpurse.web.constants.RestConstants.*;

@Service
public class CurrencyServicesImpl implements CurrencyServices {

    private final WebClient webClient;

    public CurrencyServicesImpl() {
        this.webClient = WebClient.builder().baseUrl(CURRENCY_SERVICE).build();
    }

    // Return and populate list of all currencies
    public List<CurrencyDto> getAllCurrencies(LocalDate localDate) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedString = localDate.format(formatter);

            String test = String.format(CURRENCY_VARS, formattedString,
                    CURRENCY_API_VERSION, CURRENCY_API_ENDPOINT_ALL_CURRENCIES);

            Mono<String> response = webClient.get()
                    .uri(String.format(CURRENCY_VARS, formattedString,
                            CURRENCY_API_VERSION, CURRENCY_API_ENDPOINT_ALL_CURRENCIES))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class);

            String objects = response.block();
            System.out.println(objects);
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
