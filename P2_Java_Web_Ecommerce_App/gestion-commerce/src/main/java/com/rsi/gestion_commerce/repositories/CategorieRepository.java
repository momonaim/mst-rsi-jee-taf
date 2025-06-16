package com.rsi.gestion_commerce.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rsi.gestion_commerce.models.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByNom(String nom);
}