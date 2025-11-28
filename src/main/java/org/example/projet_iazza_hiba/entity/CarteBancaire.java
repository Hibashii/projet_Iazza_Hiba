package org.example.projet_iazza_hiba.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CarteBancaire {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private CarteType type;

    private boolean active = true;

    @ManyToOne
    private Client client;
}
