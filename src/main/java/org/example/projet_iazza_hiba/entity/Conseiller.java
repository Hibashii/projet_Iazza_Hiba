package org.example.projet_iazza_hiba.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Conseiller {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "conseiller")
    private List<Client> clients = new ArrayList<>();// 10 max clients
}
