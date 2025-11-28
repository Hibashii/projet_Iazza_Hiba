package org.example.projet_iazza_hiba.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Compte {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroCompte;

    private double solde;
    private LocalDate dateOuverture;

    @ManyToOne
    private Client client;
}
