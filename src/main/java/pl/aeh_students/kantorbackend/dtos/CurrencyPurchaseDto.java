package pl.aeh_students.kantorbackend.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CurrencyPurchaseDto {

    private long accountId;
    private CurrencyCode fromCurrency;
    private CurrencyCode toCurrency;
    private BigDecimal currencyValue;
}