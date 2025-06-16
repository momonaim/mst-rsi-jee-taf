# 🛍️ Application de Gestion E-Commerce

Cette application est un système de gestion e-commerce développé avec Spring Boot qui permet de gérer efficacement les produits, les catégories et les marques. Elle offre une interface utilisateur moderne et intuitive basée sur Thymeleaf.

## 📋 Fonctionnalités

- **Gestion des Produits**

  - Ajout, modification et suppression de produits
  - Consultation détaillée des informations
  - Association avec des catégories et des marques
  - Gestion des stocks et des prix

- **Gestion des Catégories**

  - Organisation hiérarchique des produits
  - Ajout, modification et suppression de catégories
  - Vue d'ensemble des produits par catégorie

- **Gestion des Marques**

  - Suivi des différentes marques
  - Association des produits aux marques
  - Interface de gestion simplifiée

- **Interface Utilisateur**
  - Design moderne et responsive
  - Navigation intuitive
  - Pages d'erreur personnalisées
  - Thème cohérent et professionnel

## 🚀 Technologies Utilisées

- **Backend**

  - Java 17
  - Spring Boot 3.x
  - Spring Data JPA
  - H2 Database

- **Frontend**
  - Thymeleaf
  - HTML5
  - CSS3
  - Modern CSS Variables
  - Responsive Design

## 💻 Prérequis

- Java JDK 17 ou supérieur
- Maven 3.x
- Un IDE Java (Eclipse, IntelliJ IDEA, VS Code)

## 🛠️ Installation

1. Clonez le dépôt :

   ```bash
   git clone [url-du-depot]
   cd gestion-commerce
   ```

2. Compilez le projet :

   ```bash
   mvn clean install
   ```

3. Lancez l'application :

   ```bash
   mvn spring-boot:run
   ```

4. Accédez à l'application :
   ```
   http://localhost:8080
   ```

## 📁 Structure du Projet

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── rsi/
│   │           └── gestion_commerce/
│   │               ├── controllers/    # Contrôleurs REST
│   │               ├── models/         # Entités JPA
│   │               ├── repositories/   # Repositories Spring Data
│   │               └── services/       # Services métier
│   └── resources/
│       ├── static/
│       │   └── css/                   # Styles CSS
│       └── templates/                 # Templates Thymeleaf
├── test/                             # Tests unitaires et d'intégration
└── pom.xml                          # Configuration Maven
```

## 📱 Captures d'écran

- Page d'accueil : Interface principale avec accès rapide aux fonctionnalités
- Liste des produits : Vue d'ensemble des produits avec options de gestion
- Formulaires : Interfaces intuitives pour l'ajout et la modification
- Responsive : S'adapte à tous les appareils (desktop, tablette, mobile)

## 🔧 Configuration

La configuration de l'application se trouve dans `src/main/resources/application.properties`. Vous pouvez modifier :

- Port du serveur
- Configuration de la base de données
- Autres paramètres Spring Boot

## 🤝 Contribution

1. Fork le projet
2. Créez une branche pour votre fonctionnalité
3. Committez vos changements
4. Poussez vers la branche
5. Ouvrez une Pull Request

## ✍️ Auteur

- © 2025 MST-RSI | MOUADILI Abdelmounim

## 📝 Licence

Ce projet est sous licence [MIT](LICENSE).
