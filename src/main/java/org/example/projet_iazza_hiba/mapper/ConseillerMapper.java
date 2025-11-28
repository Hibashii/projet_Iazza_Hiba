package org.example.projet_iazza_hiba.mapper;

import org.example.projet_iazza_hiba.dto.ConseillerCreateDto;
import org.example.projet_iazza_hiba.dto.ConseillerDto;
import org.example.projet_iazza_hiba.dto.ConseillerUpdateDto;
import org.example.projet_iazza_hiba.entity.Conseiller;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ConseillerMapper {

    @Mapping(target = "clientIds",
            expression = "java(entity.getClients().stream().map(c -> c.getId()).toList())")
    ConseillerDto toDto(Conseiller entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clients", ignore = true)
    Conseiller toEntity(ConseillerCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clients", ignore = true)
    void updateEntity(@MappingTarget Conseiller entity, ConseillerUpdateDto dto);
}

