package pl.aeh_students.kantorbackend.services.account;

import org.springframework.stereotype.Service;
import pl.aeh_students.kantorbackend.dtos.AccountDto;
import pl.aeh_students.kantorbackend.dtos.AddAccountDto;
import pl.aeh_students.kantorbackend.dtos.AuthenticationDto;
import pl.aeh_students.kantorbackend.dtos.LoginDto;
import pl.aeh_students.kantorbackend.entities.Account;

@Service
public interface AccountService {

    long addAccount(AddAccountDto addAccountDto);
    void deleteAccount(long id);
    AccountDto getAccountDto(long id);
    Account getAccount(long id);
    AuthenticationDto login(LoginDto loginDto);
}
