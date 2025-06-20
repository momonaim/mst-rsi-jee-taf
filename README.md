# 🚗📦 Projet Java & Spring Boot – Gestion de Flotte de Véhicules et Catalogue Commercial

## 📌 Description

Ce projet est divisé en deux parties :

* **Partie 1 – Java SE :** Gestion orientée objet d’une flotte de véhicules connectés avec exception personnalisée, collections, et utilisation des Streams Java.
* **Partie 2 – Java EE (Spring Boot + Thymeleaf) :** Application web de gestion de produits, marques et catégories pour un commerce.

---

## 🧹 Partie 1 – Java SE : Gestion de Flotte de Véhicules

### 🎯 Objectif

Modéliser une flotte de véhicules connectés (voitures et motos), avec une capacité maximale de 30 véhicules, gestion via collections et traitement avec les APIs fonctionnelles Java.

### 📁 Structure

* `CapacityExceededException` : Exception personnalisée levée si la flotte dépasse 30 véhicules.
* `VehiculeConnecte` : Classe abstraite avec implémentation de `Comparator`, attributs communs, méthode `toString()`, et méthode abstraite `equals()`.
* `VoitureConnectee` : Hérite de `VehiculeConnecte`, ajoute `nombrePortes`, redéfinit `equals()`.
* `MotoConnectee` : Hérite de `VehiculeConnecte`, ajoute `typeCasque`, redéfinit `equals()`.
* `FlotteManager` : Gère les véhicules via une `Map<Long, VehiculeConnecte>`. Utilise des méthodes avec `Streams` :

  * `ajouterVehicule()`
  * `supprimerParImmatricule()`
  * `getTousLesVehicules()`
  * `compterVehiculesConnectesDepuis(int annee)`
  * `getVehiculeKilometrageMax()`
  * `afficherImmatriculationsParMarque(String marque)`

### 💡 Exemple d'utilisation (`Main.java`)

```java
FlotteManager flotte = new FlotteManager();

flotte.ajouterVehicule(1L, new VoitureConnectee("123ABC", "Toyota", "Corolla", 2019, 50000, true, 4));
flotte.ajouterVehicule(2L, new MotoConnectee("456XYZ", "Yamaha", "MT07", 2020, 12000, true, "Intégral"));

System.out.println("Kilométrage max : " + flotte.getVehiculeKilometrageMax());
System.out.println("Véhicules connectés depuis 2018 : " + flotte.compterVehiculesConnectesDepuis(2018));
```

---

## 🌐 Partie 2 – Application Web Spring Boot + Thymeleaf

### 🎯 Objectif

Développer une application de gestion de commerce avec les entités : **Produit**, **Marque** et **Catégorie**. Chaque produit est lié à une marque et une catégorie.

### ⚙️ Technologies

* Spring Boot
* Thymeleaf (templates HTML)
* Spring MVC
* Spring Data JPA
* H2 Database

### 🧱 Modèle de données

#### Produit

* `id` (Long)
* `nom` (String)
* `prix` (double)
* `Marque` (ManyToOne)
* `Categorie` (ManyToOne)

#### Marque

* `id` (Long)
* `nom` (String)

#### Catégorie

* `id` (Long)
* `nom` (String)

### 📂 Structure des contrôleurs

#### `ProduitController`

* `GET /produits` – Liste des produits
* `GET /produits/new` – Formulaire création
* `POST /produits` – Sauvegarde
* `GET /produits/edit/{id}` – Modification
* `GET /produits/delete/{id}` – Suppression

#### `MarqueController` et `CategorieController`

* Fonctions similaires à celles de `ProduitController`

### 🖼️ Vues Thymeleaf (HTML)

#### Produits

* `list.html` – Liste des produits
* `form.html` – Formulaire ajout/modification

#### Marques

* `list.html`
* `form.html`

#### Catégories

* `list.html`
* `form.html`

### 🧪 Exemple de navigation

1. Accéder à `/produits` pour voir tous les produits.
2. Cliquer sur "Ajouter un produit" pour accéder à `/produits/new`.
3. Enregistrer un produit avec une marque et une catégorie existante.

---

## ✅ Fonctionnalités Implémentées

### Partie 1 – Java

* ✅ Gestion dynamique de flotte (Ajout, Suppression, Affichage)
* ✅ Utilisation des Streams Java
* ✅ Classe abstraite + héritage
* ✅ Exception personnalisée

### Partie 2 – Web

* ✅ CRUD complet pour Produits, Marques, Catégories
* ✅ Utilisation de Spring Boot et Thymeleaf (avec contrôleurs MVC)
* ✅ Liaison entre entités via Spring Data JPA

---

## 📦 Lancement du projet

### Partie 1

Compiler avec un IDE Java ou en ligne de commande :

```bash
javac *.java
java Main
```

### Partie 2

Lancer l’application Spring Boot :

```bash
mvn spring-boot:run
```

Accéder à :

```
http://localhost:8080/produits
```

---

## ✍️ Auteur

Projet développé par **MOUADILI Abdelmounim** dans le cadre du module de JEE.
