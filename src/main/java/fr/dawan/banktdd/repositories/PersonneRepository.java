package fr.dawan.banktdd.repositories;

import fr.dawan.banktdd.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
}
