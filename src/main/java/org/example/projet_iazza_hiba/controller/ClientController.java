package org.example.projet_iazza_hiba.controller;

import lombok.RequiredArgsConstructor;
import org.example.projet_iazza_hiba.dto.ClientCreateDto;
import org.example.projet_iazza_hiba.dto.ClientDto;
import org.example.projet_iazza_hiba.dto.ClientUpdateDto;
import org.example.projet_iazza_hiba.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @GetMapping
    public List<ClientDto> getAllClients() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ClientDto getClient(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto createClient(@RequestBody ClientCreateDto dto) {
        return service.create(dto);
    }

    @PatchMapping("{id}")
    public ClientDto updateClient(@PathVariable Long id,
                            @RequestBody ClientUpdateDto dto) {
        return service.update(id, dto)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @PutMapping("{id}")
    public ClientDto replaceClient(@PathVariable Long id,
                                   @RequestBody ClientUpdateDto dto) {
        return service.update(id, dto)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        service.delete(id);
    }
}
