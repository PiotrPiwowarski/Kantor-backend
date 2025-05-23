package pl.aeh_students.kantorbackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.aeh_students.kantorbackend.dtos.AccountBalanceDto;
import pl.aeh_students.kantorbackend.dtos.AddCurrencyDto;
import pl.aeh_students.kantorbackend.dtos.CurrencyPurchaseDto;
import pl.aeh_students.kantorbackend.dtos.CurrencyWithdrawalDto;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;
import pl.aeh_students.kantorbackend.services.currency.CurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/currencies")
@Tag(name = "Currencies API")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Operation(summary = "Wpłata na konto")
    @PostMapping("/deposit")
    public ResponseEntity<Long> deposit(@RequestBody AddCurrencyDto addCurrencyDto) {
        currencyService.deposit(addCurrencyDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Wypłata z konta")
    @PostMapping("/withdrawal")
    public ResponseEntity<Void> withdrawal(@RequestBody CurrencyWithdrawalDto currencyWithdrawalDto){
        currencyService.withdrawal(currencyWithdrawalDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Pobieranie stanu konta dla konkretnej waluty")
    @GetMapping("/{accountId}/specific")
    public ResponseEntity<AccountBalanceDto> getAccountBalanceBySpecificCurrency(@PathVariable long accountId, @RequestParam CurrencyCode currencyCode) {
        AccountBalanceDto accountBalanceDto = currencyService.getAccountBalanceBySpecificCurrency(accountId, currencyCode);
        return ResponseEntity.ok(accountBalanceDto);
    }

    @Operation(summary = "Pobieranie stanu konta dla wszystkich walut")
    @GetMapping("/{accountId}")
    public ResponseEntity<List<AccountBalanceDto>> getAccountBalance(@PathVariable long accountId) {
        List<AccountBalanceDto> accountBalanceDto = currencyService.getAccountBalance(accountId);
        return ResponseEntity.ok(accountBalanceDto);
    }

    @Operation(summary = "Zakup waluty")
    @PostMapping("/currencypurchase")
    public ResponseEntity<Void> currencyPurchase(@RequestBody CurrencyPurchaseDto currencyPurchaseDto) {
        currencyService.currencyPurchase(currencyPurchaseDto);
        return ResponseEntity.ok().build();
    }
}
