package org.example.projet_iazza_hiba.dto;

import java.time.LocalDate;

public record CompteDto(
        Long id,
        String numeroCompte,
        double solde,
        LocalDate dateOuverture,
        String type,     // "COURANT" or "EPARGNE"
        Long clientId
) {}

