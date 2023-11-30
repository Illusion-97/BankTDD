package fr.dawan.banktdd.services;

import fr.dawan.banktdd.repositories.PersonneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonneService {
    private final PersonneRepository repository;
}
