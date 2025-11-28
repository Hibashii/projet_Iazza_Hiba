package org.example.projet_iazza_hiba.service;

import org.example.projet_iazza_hiba.dto.CompteCreateDto;
import org.example.projet_iazza_hiba.dto.CompteDto;
import org.example.projet_iazza_hiba.dto.CompteUpdateDto;

import java.util.List;
import java.util.Optional;

public interface CompteService {
    List<CompteDto> findAll();
    Optional<CompteDto> findById(Long id);
    CompteDto create(CompteCreateDto dto);
    Optional<CompteDto> update(Long id, CompteUpdateDto dto);
    void delete(Long id);

    // business:
    CompteDto credit(Long id, double amount);
    CompteDto debit(Long id, double amount);
    void virement(Long fromId, Long toId, double amount);
}
