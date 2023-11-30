package fr.dawan.banktdd.services;

import fr.dawan.banktdd.dtos.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll();
}
