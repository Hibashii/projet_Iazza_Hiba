package org.example.projet_iazza_hiba.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.example.projet_iazza_hiba.dto.CompteCreateDto;
import org.example.projet_iazza_hiba.dto.CompteDto;
import org.example.projet_iazza_hiba.dto.CompteUpdateDto;
import org.example.projet_iazza_hiba.entity.Client;
import org.example.projet_iazza_hiba.entity.Compte;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T16:27:54+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class CompteMapperImpl implements CompteMapper {

    @Override
    public CompteDto toDto(Compte entity) {
        if ( entity == null ) {
            return null;
        }

        Long clientId = null;
        Long id = null;
        String numeroCompte = null;
        double solde = 0.0d;
        LocalDate dateOuverture = null;

        clientId = entityClientId( entity );
        id = entity.getId();
        numeroCompte = entity.getNumeroCompte();
        solde = entity.getSolde();
        dateOuverture = entity.getDateOuverture();

        String type = entity instanceof org.example.projet_iazza_hiba.entity.CompteCourant ? "COURANT" : "EPARGNE";

        CompteDto compteDto = new CompteDto( id, numeroCompte, solde, dateOuverture, type, clientId );

        return compteDto;
    }

    @Override
    public Compte toEntity(CompteCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Compte compte = new Compte();

        compte.setNumeroCompte( dto.numeroCompte() );
        compte.setSolde( dto.solde() );

        return compte;
    }

    @Override
    public void updateEntity(Compte entity, CompteUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.numeroCompte() != null ) {
            entity.setNumeroCompte( dto.numeroCompte() );
        }
    }

    private Long entityClientId(Compte compte) {
        Client client = compte.getClient();
        if ( client == null ) {
            return null;
        }
        return client.getId();
    }
}
