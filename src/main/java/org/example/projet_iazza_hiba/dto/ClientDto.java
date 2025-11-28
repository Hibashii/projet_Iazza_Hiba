package org.example.projet_iazza_hiba.dto;

import java.util.List;

public record ClientDto(Long id,
                        String nom,
                        String prenom,
                        String adresse,
                        String codePostal,
                        String ville,
                        String telephone,
                        Long conseillerId,
                        List<Long> compteIds,
                        List<Long> carteIds)
{}
