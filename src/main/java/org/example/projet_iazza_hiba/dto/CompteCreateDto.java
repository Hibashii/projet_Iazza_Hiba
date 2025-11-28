package org.example.projet_iazza_hiba.dto;

import jakarta.validation.constraints.NotBlank;

public record CompteCreateDto(
        @NotBlank String numeroCompte,
        double solde,
        String type,   // "COURANT" or "EPARGNE"
        Long clientId
) {}

