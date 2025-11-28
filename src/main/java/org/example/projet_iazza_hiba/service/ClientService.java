package org.example.projet_iazza_hiba.service;

import org.example.projet_iazza_hiba.dto.ClientCreateDto;
import org.example.projet_iazza_hiba.dto.ClientDto;
import org.example.projet_iazza_hiba.dto.ClientUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDto> findAll();
    Optional<ClientDto> findById(Long id);
    ClientDto create(ClientCreateDto dto);
    Optional<ClientDto>  update(Long id, ClientUpdateDto dto);
    void delete(Long id);
}
