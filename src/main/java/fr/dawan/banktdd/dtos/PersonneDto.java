package fr.dawan.banktdd.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonneDto implements Serializable {
    long id;
    String name;
    List<AccountDto> accounts;
}
