package org.example.projet_iazza_hiba.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.projet_iazza_hiba.dto.CompteCreateDto;
import org.example.projet_iazza_hiba.dto.CompteDto;
import org.example.projet_iazza_hiba.dto.CompteUpdateDto;
import org.example.projet_iazza_hiba.entity.Client;
import org.example.projet_iazza_hiba.entity.Compte;
import org.example.projet_iazza_hiba.entity.CompteCourant;
import org.example.projet_iazza_hiba.entity.CompteEpargne;
import org.example.projet_iazza_hiba.mapper.CompteMapper;
import org.example.projet_iazza_hiba.repository.ClientRepository;
import org.example.projet_iazza_hiba.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompteServiceImpl implements CompteService {

    private final CompteRepository repository;
    private final ClientRepository clientRepository;
    private final CompteMapper mapper;

    @Override
    public List<CompteDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public Optional<CompteDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public CompteDto create(CompteCreateDto dto) {
        if (repository.existsByNumeroCompte(dto.numeroCompte())) {
            throw new RuntimeException("Account number already exists");
        }

        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Compte compte;

        if ("COURANT".equalsIgnoreCase(dto.type())) {
            CompteCourant c = new CompteCourant();
            c.setAutorisationDecouvert(1000.0);
            compte = c;
        } else {
            CompteEpargne c = new CompteEpargne();
            c.setTauxRemuneration(0.03);
            compte = c;
        }

        compte.setNumeroCompte(dto.numeroCompte());
        compte.setSolde(dto.solde());
        compte.setDateOuverture(LocalDate.now());
        compte.setClient(client);

        Compte saved = repository.save(compte);
        return mapper.toDto(saved);
    }

    @Transactional
    @Override
    public Optional<CompteDto> update(Long id, CompteUpdateDto dto) {
        return repository.findById(id)
                .map(entity -> {
                    mapper.updateEntity(entity, dto);
                    return mapper.toDto(entity);
                });
    }

    @Override
    public void delete(Long id) {
        Compte compte = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte not found"));
        repository.delete(compte);
    }

    // --------------- BUSINESS ---------------

    @Transactional
    @Override
    public CompteDto credit(Long id, double amount) {
        Compte compte = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte not found"));
        compte.setSolde(compte.getSolde() + amount);
        return mapper.toDto(compte);
    }

    @Transactional
    @Override
    public CompteDto debit(Long id, double amount) {
        Compte compte = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte not found"));

        if (compte instanceof CompteCourant cc) {
            if (compte.getSolde() - amount < -cc.getAutorisationDecouvert()) {
                throw new RuntimeException("Overdraft limit exceeded");
            }
        } else {
            if (compte.getSolde() - amount < 0) {
                throw new RuntimeException("Insufficient funds");
            }
        }

        compte.setSolde(compte.getSolde() - amount);
        return mapper.toDto(compte);
    }

    @Transactional
    @Override
    public void virement(Long fromId, Long toId, double amount) {
        debit(fromId, amount);
        credit(toId, amount);
    }
}

