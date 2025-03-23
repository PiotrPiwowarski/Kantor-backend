package pl.aeh_students.kantorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.aeh_students.kantorbackend.entities.Account;
import pl.aeh_students.kantorbackend.entities.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccount(Account account);
    List<Transaction> findAllByAccountAndDateTimeBetween(Account account, LocalDateTime start, LocalDateTime end);
}
