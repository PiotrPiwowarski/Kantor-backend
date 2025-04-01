package pl.aeh_students.kantorbackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.aeh_students.kantorbackend.apiResponse.ExchangeRate;
import pl.aeh_students.kantorbackend.apiResponse.ExchangeRatesTable;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;
import pl.aeh_students.kantorbackend.services.exchangeRate.ExchangeRatesService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exchangerates")
@Tag(name = "Exchange rates API")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    @Operation(summary = "Pobieranie tabeli aktualnych kursów walut")
    @GetMapping
    public ResponseEntity<ExchangeRatesTable[]> getExchangeRates() {
        ExchangeRatesTable[] response = exchangeRatesService.getExchangeRates();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Pobieranie tabeli archiwalnych kursów walut")
    @GetMapping("/archival")
    public ResponseEntity<ExchangeRatesTable[]> getArchivalExchangeRates(@RequestParam LocalDate startDate,
                                                                         @RequestParam LocalDate endDate) {
        ExchangeRatesTable[] response = exchangeRatesService.getArchivalExchangeRates(startDate, endDate);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Pobieranie kursu konkretnej waluty")
    @GetMapping("/specific")
    public ResponseEntity<ExchangeRate> getCurrencyExchangeRate(@RequestParam CurrencyCode currencyCode) {
        ExchangeRate currencyExchangeRate = exchangeRatesService.getCurrencyExchangeRate(currencyCode);
        return ResponseEntity.ok(currencyExchangeRate);
    }
}
