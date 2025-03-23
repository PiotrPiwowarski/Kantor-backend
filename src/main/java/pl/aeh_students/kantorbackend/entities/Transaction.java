package pl.aeh_students.kantorbackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;
import pl.aeh_students.kantorbackend.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    @NotNull
    private BigDecimal currencyValue;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private CurrencyCode currencyCode;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @NotNull
    private LocalDateTime dateTime;
}
