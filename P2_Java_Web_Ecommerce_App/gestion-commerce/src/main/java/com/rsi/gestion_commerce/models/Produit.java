package com.rsi.gestion_commerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Produit {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Le nom est obligatoire")
	@Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
	@Column(unique = true)
	private String nom;

	@Min(value = 0, message = "Le prix doit être supérieur ou égal à 0")
	private double prix;

	@NotNull(message = "La marque est obligatoire")
	@ManyToOne
	private Marque marque;

	@NotNull(message = "La catégorie est obligatoire")
	@ManyToOne
	private Categorie categorie;

	// public Long getId() {
	// return id;
	// }

	// public void setId(Long id) {
	// this.id = id;
	// }

	// public String getNom() {
	// return nom;
	// }

	// public void setNom(String nom) {
	// this.nom = nom;
	// }

	// public double getPrix() {
	// return prix;
	// }

	// public void setPrix(double prix) {
	// this.prix = prix;
	// }

	// public Marque getMarque() {
	// return marque;
	// }

	// public void setMarque(Marque marque) {
	// this.marque = marque;
	// }

	// public Categorie getCategorie() {
	// return categorie;
	// }

	// public void setCategorie(Categorie categorie) {
	// this.categorie = categorie;
	// }

	// @Override
	// public String toString() {
	// return "Produit{" +
	// "id=" + id +
	// ", nom='" + nom + '\'' +
	// ", prix=" + prix +
	// ", marque=" + (marque != null ? marque.getNom() : "null") +
	// ", categorie=" + (categorie != null ? categorie.getNom() : "null") +
	// '}';
	// }
}
