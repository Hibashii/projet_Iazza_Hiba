package org.example.projet_iazza_hiba.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CompteCourant extends Compte  {
    private double autorisationDecouvert = 1000.0;
}
