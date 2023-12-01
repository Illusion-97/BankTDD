package fr.dawan.banktdd.controller;

import fr.dawan.banktdd.dtos.AccountDto;
import fr.dawan.banktdd.dtos.DepositDto;
import fr.dawan.banktdd.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        List<AccountDto> all = service.getAll();
        if (all.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findById(@PathVariable long id) {
        return ResponseEntity.of(service.findById(id));
    }

    @PostMapping("/deposit")
    public void deposit(@RequestBody DepositDto dto) {
        service.deposit(dto.id(), dto.amount());
    }
}
