package org.example.projet_iazza_hiba.dto;

import jakarta.validation.constraints.NotBlank;

public record ConseillerCreateDto(
        @NotBlank String nom
) {}

