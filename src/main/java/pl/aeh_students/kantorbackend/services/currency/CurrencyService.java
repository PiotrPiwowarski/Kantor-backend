package pl.aeh_students.kantorbackend.services.currency;

import org.springframework.stereotype.Service;
import pl.aeh_students.kantorbackend.dtos.AccountBalanceDto;
import pl.aeh_students.kantorbackend.dtos.AddCurrencyDto;
import pl.aeh_students.kantorbackend.dtos.CurrencyPurchaseDto;
import pl.aeh_students.kantorbackend.dtos.CurrencyWithdrawalDto;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;

import java.util.List;

@Service
public interface CurrencyService {

    void deposit(AddCurrencyDto addCurrencyDto);
    AccountBalanceDto getAccountBalanceBySpecificCurrency(long accountId, CurrencyCode currencyCode);
    List<AccountBalanceDto> getAccountBalance(long accountId);
    void withdrawal(CurrencyWithdrawalDto currencyWithdrawalDto);
    void currencyPurchase(CurrencyPurchaseDto currencyPurchaseDto);
}
