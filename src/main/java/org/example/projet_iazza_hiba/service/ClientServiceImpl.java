package org.example.projet_iazza_hiba.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.projet_iazza_hiba.dto.ClientCreateDto;
import org.example.projet_iazza_hiba.dto.ClientDto;
import org.example.projet_iazza_hiba.dto.ClientUpdateDto;
import org.example.projet_iazza_hiba.entity.Client;
import org.example.projet_iazza_hiba.entity.Conseiller;
import org.example.projet_iazza_hiba.mapper.ClientMapper;
import org.example.projet_iazza_hiba.repository.ClientRepository;
import org.example.projet_iazza_hiba.repository.ConseillerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ConseillerRepository  conseillerRepository;
    private final ClientMapper mapper;

    @Override
    public List<ClientDto> findAll() { return clientRepository.findAll().stream().map(mapper::toDto).toList();}

    @Override
    public Optional<ClientDto>  findById(Long id) {
        return clientRepository.findById(id).map(mapper::toDto);
    }

    @Override
    public ClientDto create(ClientCreateDto dto) {
        Conseiller conseiller = null;

        if (dto.conseillerId() != null) {
            conseiller = conseillerRepository.findById(dto.conseillerId())
                    .orElseThrow(() -> new RuntimeException("Conseiller not found"));

            // max 10 clients
            if (conseiller.getClients().size() >= 10) {
                throw new RuntimeException("This conseiller already has 10 clients");
            }
        }

        Client entity = mapper.toEntity(dto);
        entity.setConseiller(conseiller); // Set conseiller

        Client saved = clientRepository.save(entity);
        return mapper.toDto(saved);
    }

    @Transactional
    @Override
    public Optional<ClientDto> update(Long id, ClientUpdateDto dto) {
        return clientRepository.findById(id)
                .map(entity -> {
                    mapper.updateEntity(entity, dto);
                    // update conseiller
                    if (dto.conseillerId() != null) {
                        Conseiller conseiller = conseillerRepository.findById(dto.conseillerId())
                                .orElseThrow(() -> new RuntimeException("Conseiller not found"));

                        // Max 10 clients per conseiller
                        if (conseiller.getClients().size() >= 10
                                && !conseiller.equals(entity.getConseiller())) {

                            // check if client is switching advisers
                            throw new RuntimeException("This conseiller already has 10 clients");
                        }

                        entity.setConseiller(conseiller);
                    }
                    return mapper.toDto(entity); // no save() needed thanks to @Transactional
                });

    }

    @Override
    public void delete(Long id) {
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        clientRepository.delete(entity);
    }
}
