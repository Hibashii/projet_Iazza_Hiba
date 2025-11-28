package org.example.projet_iazza_hiba.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.projet_iazza_hiba.dto.CompteCreateDto;
import org.example.projet_iazza_hiba.dto.CompteDto;
import org.example.projet_iazza_hiba.dto.CompteUpdateDto;
import org.example.projet_iazza_hiba.service.CompteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService service;

    @GetMapping
    public List<CompteDto> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public CompteDto getOne(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompteDto create(@RequestBody @Valid CompteCreateDto dto) {
        return service.create(dto);
    }

    @PatchMapping("{id}")
    public CompteDto update(@PathVariable Long id,
                            @RequestBody CompteUpdateDto dto) {
        return service.update(id, dto)
                .orElseThrow(() -> new RuntimeException("Compte not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // business:
    @PostMapping("{id}/credit")
    public CompteDto credit(@PathVariable Long id, @RequestParam double amount) {
        return service.credit(id, amount);
    }

    @PostMapping("{id}/debit")
    public CompteDto debit(@PathVariable Long id, @RequestParam double amount) {
        return service.debit(id, amount);
    }

    @PostMapping("/virement")
    public void virement(@RequestParam Long fromId,
                         @RequestParam Long toId,
                         @RequestParam double amount) {
        service.virement(fromId, toId, amount);
    }
}

