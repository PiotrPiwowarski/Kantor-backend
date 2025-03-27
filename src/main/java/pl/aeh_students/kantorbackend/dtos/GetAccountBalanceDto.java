package pl.aeh_students.kantorbackend.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetAccountBalanceDto {

    private long accountId;
    @NotNull
    private CurrencyCode currencyCode;
}
