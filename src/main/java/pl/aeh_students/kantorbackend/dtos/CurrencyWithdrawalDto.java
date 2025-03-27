package pl.aeh_students.kantorbackend.dtos;


import jakarta.validation.constraints.NotNull;
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
public class CurrencyWithdrawalDto {

    private long accountId;
    @NotNull
    private BigDecimal currencyValue;
    @NotNull
    private CurrencyCode currencyCode;
}