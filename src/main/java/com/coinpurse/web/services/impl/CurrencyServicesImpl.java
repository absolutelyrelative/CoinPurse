package com.coinpurse.web.services.impl;

import com.coinpurse.web.dto.currency.CurrencyDto;
import com.coinpurse.web.model.Currency;
import com.coinpurse.web.repository.CurrencyRepository;
import com.coinpurse.web.services.CurrencyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.coinpurse.web.constants.RestConstants.*;
import static tools.jackson.databind.type.LogicalType.Map;

@Service
public class CurrencyServicesImpl implements CurrencyServices {

    private final WebClient webClient;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServicesImpl(CurrencyRepository currencyRepository) {
        this.webClient = WebClient.builder().baseUrl(CURRENCY_SERVICE).build();
        this.currencyRepository = currencyRepository;
    }

    // Return and populate list of all currencies
    public Map<String, String> refreshCurrencies(LocalDate localDate) {
        Map<String, String> currencyMap = new HashMap<String, String>();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedString = localDate.format(formatter);

            Mono<Map<String, String>> response = webClient.get()
                    .uri(String.format(CURRENCY_VARS, formattedString,
                            CURRENCY_API_VERSION, CURRENCY_API_ENDPOINT_ALL_CURRENCIES))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {});

            currencyMap = response.block();
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        addMissingCurrencies(currencyMap);
        return currencyMap;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    // From a map of currencies, create and add any non-existing ones
    public void addMissingCurrencies(Map<String, String> currencies) {
        List<Currency> currencyList = getCurrencies();
        List<Currency> missingCurrencies = new ArrayList<>();

        Set<String> existingCurrencies = new HashSet<>();
        currencyList.forEach(currency -> existingCurrencies.add(currency.getCurrency()));

        for(String fetchedCurrency : currencies.keySet()) {
            if(!existingCurrencies.contains(fetchedCurrency)) {
                Currency newCurrency = new Currency();
                newCurrency.setCurrency(fetchedCurrency);
                newCurrency.setCurrencyDescription(currencies.get(fetchedCurrency));
                missingCurrencies.add(newCurrency);
            }
        }

        currencyRepository.saveAll(missingCurrencies);
    }

    // return all currencies
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }


}
