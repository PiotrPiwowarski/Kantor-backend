package pl.aeh_students.kantorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aeh_students.kantorbackend.entities.Account;
import pl.aeh_students.kantorbackend.entities.Currency;
import pl.aeh_students.kantorbackend.enums.CurrencyCode;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findByAccountAndCurrencyCode(Account account, CurrencyCode currencyCode);
    List<Currency> findAllByAccount(Account account);
}
