package fr.dawan.banktdd.services;

import fr.dawan.banktdd.dtos.AccountDto;
import fr.dawan.banktdd.repositories.AccountRepository;
import fr.dawan.banktdd.tools.DtoTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    @Override
    public List<AccountDto> getAll() {
        return repository.findAll()
                .stream()
                .map(account -> DtoTools.convert(account, AccountDto.class))
                .toList();
    }
}
