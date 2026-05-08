# Gestion Étudiants (Lab15)

## Description
Une application Android simple pour la gestion des étudiants utilisant une base de données SQLite locale. L'application permet de réaliser les opérations CRUD de base (Créer, Lire, Supprimer) sur les informations des étudiants.

## Fonctionnalités
- **Ajout d'étudiants** : Permet d'ajouter un étudiant avec son nom et son prénom.
- **Recherche d'étudiants** : Permet de trouver les détails d'un étudiant en utilisant son identifiant (ID).
- **Suppression d'étudiants** : Permet de supprimer un étudiant de la base de données par son ID.
- **Persistance des données** : Les données sont stockées localement dans une base de données SQLite nommée `ecole`.

## Architecture du Projet
Le projet suit une structure organisée :
- `projet.fst.ma.app.classes` : Modèle de données (`Etudiant`).
- `projet.fst.ma.app.service` : Logique métier pour l'accès aux données (`EtudiantService`).
- `projet.fst.ma.app.util` : Utilitaire de base de données (`MySQLiteHelper`).
- `projet.fst.ma.app` : Activité principale (`MainActivity`).

## Configuration
- **Minimum SDK** : Défini dans le fichier `build.gradle`.
- **Base de données** : SQLite (Table `etudiant` avec les colonnes `id`, `nom`, `prenom`).

## Utilisation
1. Saisissez le nom et le prénom dans les champs correspondants.
2. Cliquez sur **Valider** pour enregistrer.
3. Pour rechercher ou supprimer, saisissez l'**ID** de l'étudiant et cliquez sur le bouton correspondant.
