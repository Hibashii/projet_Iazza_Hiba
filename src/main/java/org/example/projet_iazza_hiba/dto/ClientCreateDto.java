package org.example.projet_iazza_hiba.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientCreateDto(@NotBlank String nom,
                              @NotBlank String prenom,
                              @NotBlank String adresse,
                              @NotBlank String codePostal,
                              @NotBlank String ville,
                              @NotBlank String telephone,
                              @NotNull Long conseillerId)
{}
