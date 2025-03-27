package pl.aeh_students.kantorbackend.mapper;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.aeh_students.kantorbackend.dtos.AccountDto;
import pl.aeh_students.kantorbackend.dtos.AddAccountDto;
import pl.aeh_students.kantorbackend.entities.Account;
import pl.aeh_students.kantorbackend.enums.AccountRole;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AccountMapper {

    public static Account map(AddAccountDto addAccountDto, PasswordEncoder passwordEncoder) {
        return Account.builder()
                .firstName(addAccountDto.getFirstName())
                .lastName(addAccountDto.getLastName())
                .email(addAccountDto.getEmail())
                .password(passwordEncoder.encode(addAccountDto.getPassword()))
                .accountRole(AccountRole.USER)
                .build();
    }

    public static AccountDto map(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .accountRole(account.getAccountRole())
                .build();
    }
}