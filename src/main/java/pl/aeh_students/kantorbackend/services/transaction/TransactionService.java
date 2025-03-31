package pl.aeh_students.kantorbackend.services.transaction;

import org.springframework.stereotype.Service;
import pl.aeh_students.kantorbackend.dtos.GetTransactionDto;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;
import pl.aeh_students.kantorbackend.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public interface TransactionService {

    void addTransaction(TransactionType transactionType, BigDecimal currencyValue, CurrencyCode currencyCode, long accountId);
    List<GetTransactionDto> getAllTransactions(long accountId);
    List<GetTransactionDto> getTransactionsOfDate(long accountId, LocalDate date);
}
