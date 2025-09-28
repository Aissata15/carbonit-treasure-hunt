# Architecture du projet

## Structure des dossiers

```
carbonit-treasure-hunt/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── carbonit/
│   │               ├── TreasureHuntApplication.java   # Point d'entrée de l'application, lance la simulation
│   │               ├── model/
│   │               │   ├── Adventurer.java            # Représente un aventurier (nom, position, orientation, mouvements, trésors ramassés)
│   │               │   ├── Map.java                   # Représente la carte, gère les montagnes et les trésors
│   │               │   ├── Position.java              # Représente une position (x, y) sur la carte
│   │               │   ├── Treasure.java              # Représente un trésor sur la carte
│   │               │   ├── Orientation.java           # Enum pour l'orientation (N, S, E, O)
│   │               │   └── MapCell.java               # Enum pour le type de case (PLAIN, MOUNTAIN, TREASURE)
│   │               ├── repository/
│   │               │   ├── InputFileRepository.java   # Lecture du fichier d'entrée et création des objets du jeu
│   │               │   └── OutputFileRepository.java  # Écriture du résultat de la simulation dans le fichier de sortie
│   │               └── service/
│   │                   └── MovementService.java       # Logique de déplacement des aventuriers
│   └── test/
│       └── java/
│           └── com/
│               └── carbonit/
│                   ├── model/                        # Tests unitaires des modèles
│                   ├── repository/                   # Tests unitaires des repositories
│                   └── service/                      # Tests unitaires des services
│
├── pom.xml            # Configuration Maven (dépendances, build)
├── README.md          # Instructions d'installation, d'utilisation et exemples de scénarios
├── ARCHITECTURE.md    # Description de l'architecture et du rôle des fichiers
└── solid_principles.md# Explication du respect des principes SOLID
```

## Fonction des fichiers principaux

- **TreasureHuntApplication.java** : Lance la simulation, lit le fichier d'entrée, exécute les mouvements, écrit le résultat.
- **model/** : Contient les classes qui représentent les éléments du jeu (aventurier, carte, trésor, position, orientation, type de case).
- **repository/** : Gère la lecture (`InputFileRepository.java`) et l’écriture (`OutputFileRepository.java`) des fichiers.
- **service/** : Contient la logique métier, notamment le déplacement des aventuriers (`MovementService.java`).
- **test/** : Contient les tests unitaires pour vérifier le bon fonctionnement de chaque composant.
- **pom.xml** : Fichier de configuration Maven pour compiler, tester et packager le projet.
- **README.md** : Documentation utilisateur, instructions d’installation, d’utilisation et scénarios de test.
- **ARCHITECTURE.md** : Présente l’organisation du projet et le rôle de chaque fichier.
- **solid_principles.md** : Explique comment le projet respecte les principes SOLID.

## Fonctionnement global

1. **Lecture du fichier d'entrée** : Création de la carte, des montagnes, des trésors et des aventuriers.
2. **Simulation** : Tour par tour, chaque aventurier exécute ses mouvements en respectant les règles (montagnes, autres aventuriers, collecte des trésors).
3. **Écriture du résultat** : L’état final de la carte et des aventuriers est écrit dans le fichier de sortie.

## Règles prises en compte

- Les aventuriers ne peuvent pas traverser les montagnes.
- Un aventurier ne peut pas se déplacer sur une case occupée par un autre aventurier.
- Un trésor est ramassé uniquement lorsqu’un aventurier entre sur la case.
- Les lignes du fichier d’entrée commençant par `#` sont ignorées (commentaires).

---

Ce projet est conçu pour être évolutif, maintenable et facilement testable.