package org.example.projet_iazza_hiba.mapper;

import org.example.projet_iazza_hiba.dto.CompteCreateDto;
import org.example.projet_iazza_hiba.dto.CompteDto;
import org.example.projet_iazza_hiba.dto.CompteUpdateDto;
import org.example.projet_iazza_hiba.entity.Compte;
import org.mapstruct.*;
import org.example.projet_iazza_hiba.entity.CompteCourant;
import org.example.projet_iazza_hiba.entity.CompteEpargne;

@Mapper(componentModel = "spring")
public interface CompteMapper {

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "type", expression = "java(entity instanceof org.example.projet_iazza_hiba.entity.CompteCourant ? \"COURANT\" : \"EPARGNE\")")
    CompteDto toDto(Compte entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    Compte toEntity(CompteCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    void updateEntity(@MappingTarget Compte entity, CompteUpdateDto dto);
}
