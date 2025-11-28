package org.example.projet_iazza_hiba.dto;

import java.util.List;

public record ConseillerDto(
        Long id,
        String nom,
        List<Long> clientIds
) {}
