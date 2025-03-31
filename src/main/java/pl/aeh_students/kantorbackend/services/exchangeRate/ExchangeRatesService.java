package pl.aeh_students.kantorbackend.services.exchangeRate;

import org.springframework.stereotype.Service;
import pl.aeh_students.kantorbackend.apiResponse.ExchangeRate;
import pl.aeh_students.kantorbackend.apiResponse.ExchangeRatesTable;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;

import java.time.LocalDate;


@Service
public interface ExchangeRatesService {

    ExchangeRatesTable[] getExchangeRates();
    ExchangeRatesTable[] getArchivalExchangeRates(LocalDate startDate, LocalDate endDate);
    ExchangeRate getCurrencyExchangeRate(CurrencyCode currencyCode);
}
