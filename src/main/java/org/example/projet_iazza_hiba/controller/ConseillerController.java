package org.example.projet_iazza_hiba.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.projet_iazza_hiba.dto.ConseillerCreateDto;
import org.example.projet_iazza_hiba.dto.ConseillerDto;
import org.example.projet_iazza_hiba.dto.ConseillerUpdateDto;
import org.example.projet_iazza_hiba.service.ConseillerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conseillers")
@RequiredArgsConstructor
public class ConseillerController {

    private final ConseillerService service;
    @GetMapping
    public List<ConseillerDto> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ConseillerDto getOne(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new RuntimeException("Conseiller not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConseillerDto create(@RequestBody @Valid ConseillerCreateDto dto) {
        return service.create(dto);
    }

    @PatchMapping("{id}")
    public ConseillerDto update(@PathVariable Long id,
                                @RequestBody ConseillerUpdateDto dto) {
        return service.update(id, dto)
                .orElseThrow(() -> new RuntimeException("Conseiller not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
