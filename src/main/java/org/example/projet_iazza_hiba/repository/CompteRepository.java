package org.example.projet_iazza_hiba.repository;

import org.example.projet_iazza_hiba.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
