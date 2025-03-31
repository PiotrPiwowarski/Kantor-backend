package pl.aeh_students.kantorbackend.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExchangeRatesTable {

    private String table;
    private String no;
    private LocalDate tradingDate;
    private LocalDate effectiveDate;
    private List<Rate> rates;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Rate {
        private String currency;
        private CurrencyCode code;
        private BigDecimal bid;
        private BigDecimal ask;
    }
}
