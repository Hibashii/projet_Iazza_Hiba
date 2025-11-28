package org.example.projet_iazza_hiba.mapper;

import java.util.List;
import javax.annotation.processing.Generated;
import org.example.projet_iazza_hiba.dto.ClientCreateDto;
import org.example.projet_iazza_hiba.dto.ClientDto;
import org.example.projet_iazza_hiba.dto.ClientUpdateDto;
import org.example.projet_iazza_hiba.entity.Client;
import org.example.projet_iazza_hiba.entity.Conseiller;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T14:53:06+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDto toDto(Client entity) {
        if ( entity == null ) {
            return null;
        }

        Long conseillerId = null;
        Long id = null;
        String nom = null;
        String prenom = null;
        String adresse = null;
        String codePostal = null;
        String ville = null;
        String telephone = null;

        conseillerId = entityConseillerId( entity );
        id = entity.getId();
        nom = entity.getNom();
        prenom = entity.getPrenom();
        adresse = entity.getAdresse();
        codePostal = entity.getCodePostal();
        ville = entity.getVille();
        telephone = entity.getTelephone();

        List<Long> compteIds = entity.getComptes().stream().map(c -> c.getId()).toList();
        List<Long> carteIds = entity.getCartes().stream().map(c -> c.getId()).toList();

        ClientDto clientDto = new ClientDto( id, nom, prenom, adresse, codePostal, ville, telephone, conseillerId, compteIds, carteIds );

        return clientDto;
    }

    @Override
    public Client toEntity(ClientCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Client client = new Client();

        client.setNom( dto.nom() );
        client.setPrenom( dto.prenom() );
        client.setAdresse( dto.adresse() );
        client.setCodePostal( dto.codePostal() );
        client.setVille( dto.ville() );
        client.setTelephone( dto.telephone() );

        return client;
    }

    @Override
    public void updateEntity(Client entity, ClientUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.nom() != null ) {
            entity.setNom( dto.nom() );
        }
        if ( dto.prenom() != null ) {
            entity.setPrenom( dto.prenom() );
        }
        if ( dto.adresse() != null ) {
            entity.setAdresse( dto.adresse() );
        }
        if ( dto.codePostal() != null ) {
            entity.setCodePostal( dto.codePostal() );
        }
        if ( dto.ville() != null ) {
            entity.setVille( dto.ville() );
        }
        if ( dto.telephone() != null ) {
            entity.setTelephone( dto.telephone() );
        }
    }

    private Long entityConseillerId(Client client) {
        Conseiller conseiller = client.getConseiller();
        if ( conseiller == null ) {
            return null;
        }
        return conseiller.getId();
    }
}
