package fr.dawan.banktdd.controller;

import fr.dawan.banktdd.dtos.AccountDto;
import fr.dawan.banktdd.dtos.DepositDto;
import fr.dawan.banktdd.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    @GetMapping
    public List<AccountDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable long id) {
        return service.findById(id).orElse(null);
    }

    @PostMapping("/deposit")
    public void deposit(@RequestBody DepositDto dto) {
        service.deposit(dto.id(), dto.amount());
    }
}
