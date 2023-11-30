package fr.dawan.banktdd.repositories;

import fr.dawan.banktdd.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
