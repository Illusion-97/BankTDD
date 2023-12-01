package fr.dawan.banktdd.services;

import fr.dawan.banktdd.dtos.AccountDto;
import fr.dawan.banktdd.models.Account;
import fr.dawan.banktdd.repositories.AccountRepository;
import fr.dawan.banktdd.tools.DtoTools;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<AccountDto> findById(long id) {
        return repository.findById(id).map(account -> DtoTools.convert(account, AccountDto.class));
    }

    @EventListener(ApplicationStartedEvent.class)
    public void initAccounts() {
        System.out.println("\u001B[36mAccountServiceImpl.initAccounts\u001B[0m");
        repository.saveAll(List.of(
                new Account(0,50),
                new Account(0,123),
                new Account(0,456),
                new Account(0,789),
                new Account(0,741)
                ));
    }
}
