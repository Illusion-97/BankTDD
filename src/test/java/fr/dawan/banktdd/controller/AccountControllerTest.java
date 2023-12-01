package fr.dawan.banktdd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.banktdd.dtos.AccountDto;
import fr.dawan.banktdd.dtos.DepositDto;
import fr.dawan.banktdd.services.AccountService;
import fr.dawan.banktdd.services.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    AccountController controller;

    @MockBean // Création de Mock dans un cadre de tests d'intégration
    AccountService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        List<AccountDto> dtos = List.of(new AccountDto());
        Mockito.when(service.getAll()).thenReturn(dtos);

        mockMvc.perform( // Simule une requete vers l'api
                get("/accounts")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void findByExistingId() throws Exception {
        long id = 5;
        AccountDto dto = new AccountDto(id, 500);
        Mockito.when(service.findById(id)).thenReturn(Optional.of(dto));

        mockMvc.perform( // Simule une requete vers l'api
                        get("/accounts/"+ id)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$").value(dto));
    }

    @Test
    void findByNonExistingId() throws Exception {
        long id = 5;
        Mockito.when(service.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform( // Simule une requete vers l'api
                        get("/accounts/"+ id)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotHaveJsonPath()); // test les retours null
    }

    @Test
    void deposit() throws Exception {
        long id = 5;
        double amount = 500;
        DepositDto body = new DepositDto(id, amount);
        Mockito.doNothing().when(service).deposit(id,amount);

        mockMvc.perform( // Simule une requete vers l'api
                        post("/accounts/deposit")
                                .contentType(MediaType.APPLICATION_JSON) // Précise le type du body
                                .content(toJson(body)) // body du post (à convertir en json)
                                //.with(user("user").password("pswrd").roles("TEST"))) Pour SpringSecurity
                ).andExpect(status().isOk());
    }

    private String toJson(Object object) {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
