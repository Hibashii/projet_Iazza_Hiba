package org.example.projet_iazza_hiba.mapper;

import java.util.List;
import javax.annotation.processing.Generated;
import org.example.projet_iazza_hiba.dto.ConseillerCreateDto;
import org.example.projet_iazza_hiba.dto.ConseillerDto;
import org.example.projet_iazza_hiba.dto.ConseillerUpdateDto;
import org.example.projet_iazza_hiba.entity.Conseiller;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T15:31:10+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class ConseillerMapperImpl implements ConseillerMapper {

    @Override
    public ConseillerDto toDto(Conseiller entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String nom = null;

        id = entity.getId();
        nom = entity.getNom();

        List<Long> clientIds = entity.getClients().stream().map(c -> c.getId()).toList();

        ConseillerDto conseillerDto = new ConseillerDto( id, nom, clientIds );

        return conseillerDto;
    }

    @Override
    public Conseiller toEntity(ConseillerCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Conseiller conseiller = new Conseiller();

        conseiller.setNom( dto.nom() );

        return conseiller;
    }

    @Override
    public void updateEntity(Conseiller entity, ConseillerUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.nom() != null ) {
            entity.setNom( dto.nom() );
        }
    }
}
