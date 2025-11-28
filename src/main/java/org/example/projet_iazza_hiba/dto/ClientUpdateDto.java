package org.example.projet_iazza_hiba.dto;

public record ClientUpdateDto(String nom,
                              String prenom,
                              String adresse,
                              String codePostal,
                              String ville,
                              String telephone,
                              Long conseillerId)
{}
