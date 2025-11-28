package org.example.projet_iazza_hiba.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CompteEpargne extends Compte {

    private double tauxRemuneration = 0.03;
}
