package pl.aeh_students.kantorbackend.services.transaction.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.aeh_students.kantorbackend.dtos.GetTransactionDto;
import pl.aeh_students.kantorbackend.entities.Account;
import pl.aeh_students.kantorbackend.entities.Transaction;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;
import pl.aeh_students.kantorbackend.enums.TransactionType;
import pl.aeh_students.kantorbackend.repositories.TransactionRepository;
import pl.aeh_students.kantorbackend.services.account.AccountService;
import pl.aeh_students.kantorbackend.services.transaction.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public void addTransaction(TransactionType transactionType, BigDecimal currencyValue, CurrencyCode currencyCode, long accountId) {
        Account account = accountService.getAccount(accountId);
        Transaction transaction = Transaction.builder()
                .transactionType(transactionType)
                .currencyValue(currencyValue)
                .currencyCode(currencyCode)
                .account(account)
                .dateTime(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);
    }

    @Override
    public List<GetTransactionDto> getAllTransactions(long accountId) {
        Account account = accountService.getAccount(accountId);
        List<Transaction> transactions = transactionRepository.findAllByAccount(account);
        return transactions.stream()
                .map(transaction -> GetTransactionDto.builder()
                        .id(transaction.getId())
                        .transactionType(transaction.getTransactionType())
                        .currencyValue(transaction.getCurrencyValue())
                        .currencyCode(transaction.getCurrencyCode())
                        .accountId(account.getId())
                        .dateTime(transaction.getDateTime())
                        .build())
                .toList();
    }

    @Override
    public List<GetTransactionDto> getTransactionsOfDate(long accountId, LocalDate date) {
        Account account = accountService.getAccount(accountId);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        List<Transaction> transactions = transactionRepository.findAllByAccountAndDateTimeBetween(account, startOfDay, endOfDay);
        return transactions.stream()
                .map(transaction -> GetTransactionDto.builder()
                        .id(transaction.getId())
                        .transactionType(transaction.getTransactionType())
                        .currencyValue(transaction.getCurrencyValue())
                        .currencyCode(transaction.getCurrencyCode())
                        .accountId(account.getId())
                        .dateTime(transaction.getDateTime())
                        .build())
                .toList();
    }
}
