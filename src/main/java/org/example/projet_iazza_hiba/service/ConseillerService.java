package org.example.projet_iazza_hiba.service;

import org.example.projet_iazza_hiba.dto.ConseillerCreateDto;
import org.example.projet_iazza_hiba.dto.ConseillerDto;
import org.example.projet_iazza_hiba.dto.ConseillerUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ConseillerService {
    List<ConseillerDto> findAll();
    Optional<ConseillerDto> findById(Long id);
    ConseillerDto create(ConseillerCreateDto dto);
    Optional<ConseillerDto> update(Long id, ConseillerUpdateDto dto);
    void delete(Long id);
}

