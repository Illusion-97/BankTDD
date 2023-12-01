package fr.dawan.banktdd.services;

import fr.dawan.banktdd.dtos.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountDto> getAll();

    Optional<AccountDto> findById(long id);
}
