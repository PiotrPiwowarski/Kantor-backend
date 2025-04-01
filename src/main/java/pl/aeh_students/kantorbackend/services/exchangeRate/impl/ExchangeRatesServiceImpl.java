package pl.aeh_students.kantorbackend.services.exchangeRate.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.aeh_students.kantorbackend.apiResponse.ExchangeRate;
import pl.aeh_students.kantorbackend.apiResponse.ExchangeRatesTable;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;
import pl.aeh_students.kantorbackend.exceptions.EndDateIsBeforeStartDateException;
import pl.aeh_students.kantorbackend.services.exchangeRate.ExchangeRatesService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    @Override
    public ExchangeRatesTable[] getExchangeRates() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.nbp.pl")
                .build();

        return webClient.get()
                .uri("/api/exchangerates/tables/C?format=json")
                .retrieve()
                .bodyToMono(ExchangeRatesTable[].class)
                .block();
    }

    @Override
    public ExchangeRatesTable[] getArchivalExchangeRates(LocalDate startDate, LocalDate endDate) {
        if(endDate.isBefore(startDate)) {
            throw new EndDateIsBeforeStartDateException();
        }
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.nbp.pl")
                .build();

        return webClient.get()
                .uri("/api/exchangerates/tables/C/" + startDate.toString() + "/" + endDate.toString() + "?format=json")
                .retrieve()
                .bodyToMono(ExchangeRatesTable[].class)
                .block();
    }

    @Override
    public ExchangeRate getCurrencyExchangeRate(CurrencyCode currencyCode) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.nbp.pl")
                .build();

        return webClient.get()
                .uri("/api/exchangerates/rates/C/USD?format=json")
                .retrieve()
                .bodyToMono(ExchangeRate.class)
                .block();
    }
}
