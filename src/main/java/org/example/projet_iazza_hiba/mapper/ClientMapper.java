package org.example.projet_iazza_hiba.mapper;

import org.example.projet_iazza_hiba.dto.ClientCreateDto;
import org.example.projet_iazza_hiba.dto.ClientDto;
import org.example.projet_iazza_hiba.dto.ClientUpdateDto;
import org.example.projet_iazza_hiba.entity.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClientMapper {

    @Mapping(target = "conseillerId", source = "conseiller.id")
    @Mapping(target = "compteIds", expression = "java(entity.getComptes().stream().map(c -> c.getId()).toList())")
    @Mapping(target = "carteIds", expression = "java(entity.getCartes().stream().map(c -> c.getId()).toList())")
    ClientDto toDto(Client entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comptes", ignore = true)
    @Mapping(target = "cartes", ignore = true)
    @Mapping(target = "conseiller", ignore = true)
    Client toEntity(ClientCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comptes", ignore = true)
    @Mapping(target = "cartes", ignore = true)
    @Mapping(target = "conseiller", ignore = true)
    void updateEntity(@MappingTarget Client entity, ClientUpdateDto dto);
}
