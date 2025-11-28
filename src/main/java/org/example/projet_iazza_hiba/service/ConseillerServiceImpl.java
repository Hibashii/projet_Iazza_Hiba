package org.example.projet_iazza_hiba.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.projet_iazza_hiba.dto.ConseillerCreateDto;
import org.example.projet_iazza_hiba.dto.ConseillerDto;
import org.example.projet_iazza_hiba.dto.ConseillerUpdateDto;
import org.example.projet_iazza_hiba.entity.Conseiller;
import org.example.projet_iazza_hiba.mapper.ConseillerMapper;
import org.example.projet_iazza_hiba.repository.ConseillerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConseillerServiceImpl implements ConseillerService {

    private final ConseillerRepository repository;
    private final ConseillerMapper mapper;

    @Override
    public List<ConseillerDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public Optional<ConseillerDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public ConseillerDto create(ConseillerCreateDto dto) {
        Conseiller entity = mapper.toEntity(dto);
        Conseiller saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Transactional
    @Override
    public Optional<ConseillerDto> update(Long id, ConseillerUpdateDto dto) {
        return repository.findById(id)
                .map(entity -> {
                    mapper.updateEntity(entity, dto);
                    return mapper.toDto(entity);
                });
    }

    @Override
    public void delete(Long id) {
        Conseiller entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conseiller not found"));
        repository.delete(entity);
    }
}

