package pl.aeh_students.kantorbackend.services.account.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aeh_students.kantorbackend.dtos.AccountDto;
import pl.aeh_students.kantorbackend.dtos.AddAccountDto;
import pl.aeh_students.kantorbackend.dtos.AuthenticationDto;
import pl.aeh_students.kantorbackend.dtos.LoginDto;
import pl.aeh_students.kantorbackend.entities.Account;
import pl.aeh_students.kantorbackend.entities.Token;
import pl.aeh_students.kantorbackend.exceptions.AccountWithSuchEmailAlreadyExistsException;
import pl.aeh_students.kantorbackend.exceptions.NoAccountWithSuchIdException;
import pl.aeh_students.kantorbackend.mapper.AccountMapper;
import pl.aeh_students.kantorbackend.repositories.AccountRepository;
import pl.aeh_students.kantorbackend.repositories.TokenRepository;
import pl.aeh_students.kantorbackend.services.account.AccountService;
import pl.aeh_students.kantorbackend.services.jwt.JwtService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Override
    public long addAccount(AddAccountDto addAccountDto) {
        if(addAccountDto.getEmail().length() == 0 || addAccountDto.getPassword().length() == 0 || addAccountDto.getFirstName().length() == 0 || addAccountDto.getLastName().length() == 0) {
            throw new IllegalStateException("Błędne dane użytkownika");
        }
        Optional<Account> optionalAccount = accountRepository.findByEmail(addAccountDto.getEmail());
        if(optionalAccount.isPresent()) {
            throw new AccountWithSuchEmailAlreadyExistsException();
        }
        Account account = AccountMapper.map(addAccountDto,passwordEncoder);
        Account savedAccount = accountRepository.save(account);
        return savedAccount.getId();
    }

    @Override
    public void deleteAccount(long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto getAccountDto(long id) {
        Account account = accountRepository.findById(id).orElseThrow(NoAccountWithSuchIdException::new);
        return AccountMapper.map(account);
    }

    @Override
    public Account getAccount(long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isEmpty()) {
            throw new NoAccountWithSuchIdException();
        }
        return optionalAccount.get();
    }

    @Transactional
    @Override
    public AuthenticationDto login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        Account account = accountRepository
                .findByEmail(loginDto.getEmail())
                .orElseThrow(NoAccountWithSuchIdException::new);
        String jwtToken = jwtService.generateToken(account);
        tokenRepository.deleteAllByAccount(account);
        saveUserToken(jwtToken, account);
        return AuthenticationDto.builder()
                .accountId(account.getId())
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(String jwtToken, Account account) {
        Token token = Token.builder()
                .token(jwtToken)
                .account(account)
                .build();
        tokenRepository.save(token);
    }
}
