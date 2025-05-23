package pl.aeh_students.kantorbackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.aeh_students.kantorbackend.dtos.AccountDto;
import pl.aeh_students.kantorbackend.dtos.AddAccountDto;
import pl.aeh_students.kantorbackend.dtos.AuthenticationDto;
import pl.aeh_students.kantorbackend.dtos.LoginDto;
import pl.aeh_students.kantorbackend.services.account.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@Tag(name = "Accounts API")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Dodawanie nowego konta")
    @PostMapping("/register")
    public ResponseEntity<Long> addAccount(@RequestBody AddAccountDto addAccountDto) {
        long id = accountService.addAccount(addAccountDto);
        return ResponseEntity.ok(id);
    }

    @Operation(summary = "Usuwanie konta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Pobieranie konta")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable long id) {
        AccountDto accountDto = accountService.getAccountDto(id);
        return ResponseEntity.ok(accountDto);
    }

    @Operation(summary = "Zalogowanie")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(@RequestBody LoginDto loginDto) {
        AuthenticationDto authenticationDto = accountService.login(loginDto);
        return ResponseEntity.ok(authenticationDto);
    }

    @Operation(summary = "Wylogowanie")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}
