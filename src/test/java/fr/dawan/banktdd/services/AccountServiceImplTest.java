package fr.dawan.banktdd.services;

import fr.dawan.banktdd.dtos.AccountDto;
import fr.dawan.banktdd.models.Account;
import fr.dawan.banktdd.repositories.AccountRepository;
import fr.dawan.banktdd.tools.DtoTools;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// ArgumentMatchers fournit des méthodes pour se substituer à des variables fixées pour les mocks
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    private AccountServiceImpl service;
    @Mock
    private AccountRepository repository;

    @BeforeEach
    void init() {
        service = new AccountServiceImpl(repository);
    }
    @Test
    void getAll() {
        // Ceci n'est pas un test unitaire valable puisque laisse passer des informations par la méthode DtoTools.convert()
        List<AccountDto> result = service.getAll();
        assertEquals(0, result.size());
    }
    @Test
    void getAllWith2Elements() {
        Account account = new Account(1, 50);
        Account account1 = new Account(2, 500);
        AccountDto accountDto = new AccountDto(1, 50);
        AccountDto accountDto1 = new AccountDto(2, 500);


        List<AccountDto> expected = List.of(
                accountDto,
                accountDto1
        );

        try (MockedStatic<DtoTools> mockDtoTools = Mockito.mockStatic(DtoTools.class)) {
            Mockito.when(repository.findAll())
                    .thenReturn(List.of(
                            account,
                            account1
                    ));

            mockDtoTools.when(() -> DtoTools.convert(account, AccountDto.class)).thenReturn(accountDto);
            mockDtoTools.when(() -> DtoTools.convert(account1, AccountDto.class)).thenReturn(accountDto1);

            List<AccountDto> result = service.getAll();
            assertEquals(expected, result);

        }
    }

    @Test
    void getAllWith4Elements() {

        List<Account> accounts = List.of(
                new Account(1, 50),
                new Account(2, 500),
                new Account(3, 700),
                new Account(4, 800)
        );
        List<AccountDto> expected = List.of(
                new AccountDto(1, 50),
                new AccountDto(2, 500),
                new AccountDto(3, 700),
                new AccountDto(4, 800)
        );

        try (MockedStatic<DtoTools> mockDtoTools = Mockito.mockStatic(DtoTools.class)) {
            Mockito.when(repository.findAll())
                    .thenReturn(accounts);
            mockConvert(mockDtoTools);

            List<AccountDto> result = service.getAll();
            assertEquals(expected, result);

        }
    }



    /*
     * Lorsqu'il sera demandé à DtoTools de convertir n'importe quel objet de type Account en AccountDto
     * Alors on ira chercher dans la liste d'account l'index de cet objet
     * pour retourner le dto récupéré dans la liste attendue a l'index récupéré
     * */
    private void mockConvert(MockedStatic<DtoTools> mockDtoTools) {
        mockDtoTools.when(() -> DtoTools.convert(any(Account.class), AccountDto.class)).thenAnswer(invocation -> {
            // Récupère une variable passée à un mock
            Account a = invocation.getArgument(0);
            return new AccountDto(a.getId(), a.getBalance());
        });
    }


}
