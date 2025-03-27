package pl.aeh_students.kantorbackend.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;
import pl.aeh_students.kantorbackend.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetTransactionDto {

    private long id;
    private TransactionType transactionType;
    private BigDecimal currencyValue;
    private CurrencyCode currencyCode;
    private long accountId;
    private LocalDateTime dateTime;
}