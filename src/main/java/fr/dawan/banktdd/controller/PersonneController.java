package fr.dawan.banktdd.controller;

import fr.dawan.banktdd.services.PersonneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonneController {
    private final PersonneService service;
}
