package com.coinpurse.web.services.impl;

import com.coinpurse.web.infrastructure.client.CurrencyApiClient;
import com.coinpurse.web.model.Currency;
import com.coinpurse.web.repository.CurrencyRepository;
import com.coinpurse.web.services.CurrencyServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CurrencyServicesImpl implements CurrencyServices {

    private static final Logger log = LoggerFactory.getLogger(CurrencyServicesImpl.class);
    private final CurrencyApiClient currencyApiClient;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServicesImpl(CurrencyApiClient currencyApiClient, CurrencyRepository currencyRepository) {
        this.currencyApiClient = currencyApiClient;
        this.currencyRepository = currencyRepository;
    }

    // Return and populate list of all currencies
    @Override
    @Transactional
    public Mono<Map<String, String>> refreshCurrencies(LocalDate localDate) {
        currencyApiClient.fetchCurrenciesByDate(localDate)
            .map(fetchedMono -> {
                addMissingCurrencies(fetchedMono);
                return fetchedMono;
            });

        return Mono.just(Collections.emptyMap());
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

        // Update existing currencies
        currencyList.forEach(currency -> currency.setUpdatedon(LocalDateTime.now()));
        currencyRepository.saveAll(currencyList);
    }

    // return all currencies
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }


}
