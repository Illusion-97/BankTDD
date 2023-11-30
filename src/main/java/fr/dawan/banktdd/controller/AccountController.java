package fr.dawan.banktdd.controller;

import fr.dawan.banktdd.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;
}
