package fr.dawan.banktdd.controller;

import fr.dawan.banktdd.dtos.AccountDto;
import fr.dawan.banktdd.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
